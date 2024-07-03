package com.Yu.Pan.service.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.Yu.Pan.cache.core.constants.CacheConstants;
import com.Yu.Pan.core.exception.BizException;
import com.Yu.Pan.core.generator.help.MyBatisWrapper;
import com.Yu.Pan.core.response.ResponseCode;
import com.Yu.Pan.core.utils.IdUtil;
import com.Yu.Pan.core.utils.JwtUtil;
import com.Yu.Pan.core.utils.PasswordUtil;
import com.Yu.Pan.service.constants.CreateFolderConstants;
import com.Yu.Pan.service.constants.UserConstants;
import com.Yu.Pan.service.context.*;
import com.Yu.Pan.service.converter.UserConverter;
import com.Yu.Pan.service.domain.User;
import com.Yu.Pan.service.domain.UserField;
import com.Yu.Pan.service.domain.UserFile;
import com.Yu.Pan.service.domain.UserFileField;
import com.Yu.Pan.service.enums.DelFlagEnum;
import com.Yu.Pan.service.enums.FolderFlagEnum;
import com.Yu.Pan.service.mapper.UserFileMapper;
import com.Yu.Pan.service.mapper.UserMapper;
import com.Yu.Pan.service.resp.UserInfoResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 11:25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    final UserMapper userMapper;
    final UserFileService userFileService;
    final UserFileMapper userFileMapper;
    @Resource
    CacheManager cacheManager;

    @Transactional
    public Long register(UserContext context) throws NoSuchAlgorithmException {
        assemblyUserContext(context);
///注册用户
        reg(context);
        //创建用户根目录
        Long userRoot = createUserRoot(context);
        log.info("用户文件{}创建成功", userRoot);
        return context.getUser().getId();
    }

    public String login(UserLoginContext context) throws NoSuchAlgorithmException {
        checkLoginInfo(context);
        generateToken(context);
        return context.getAccessToken();
    }

    public UserInfoResp info(Long userId) {
        MyBatisWrapper<User> wrapper = new MyBatisWrapper<>();
        wrapper.select(UserField.Avatar, UserField.Username).whereBuilder().andEq(UserField.setId(userId));
        User user = userMapper.topOne(wrapper);
        if (ObjectUtil.isNull(user)) {
            throw new BizException(ResponseCode.USER_NOT_EXIST);
        }
        MyBatisWrapper<UserFile> wrapper1 = new MyBatisWrapper<>();
        wrapper1.select(UserFileField.FileId, UserFileField.Filename).whereBuilder()
                .andEq(UserFileField.setDelFlag(DelFlagEnum.NO.getFlag()))
                .andEq(UserFileField.setFolderFlag(FolderFlagEnum.TRUE.getFlag()))
                .andEq(UserFileField.setUserId(userId))
                .andEq(UserFileField.setParentId(CreateFolderConstants.TOP_PARENT_ID));
        UserFile userFile = userFileMapper.topOne(wrapper1);
        UserInfoResp userInfoResp = UserConverter.INSTANCE.toUserInfoResp(user, userFile);
        return userInfoResp;
    }
    public void exit(Long userId) {
        try {
            Cache cache = cacheManager.getCache(CacheConstants.YU_PAN_CACHE);
            cache.evict(UserConstants.USER_LOGIN_PREFIX + userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("退出登录失败");
        }
    }

    public String checkName(CheckNameContext context) {
        MyBatisWrapper<User> wrapper = new MyBatisWrapper<>();
        wrapper.select(UserField.Question).whereBuilder().andEq(UserField.setUsername(context.getUsername()));
        User user = userMapper.topOne(wrapper);
        if (ObjectUtil.isNull(user)) {
            throw new BizException(ResponseCode.USER_NOT_EXIST);
        }
        return user.getQuestion();
    }


    //============================================private======================================================
    private void assemblyUserContext(UserContext context) throws NoSuchAlgorithmException {
        User user = UserConverter.INSTANCE.toUser(context);
        DateTime now = DateTime.now();
        user.setId(IdUtil.getSnowFlaskId());
        log.info("Password:{}", user.getPassword());
        String salt = PasswordUtil.genSalt(), dbPassword = PasswordUtil.encryptPassword(context.getPassword(), salt);
        log.info("Reg:{},{},{}", user.getPassword(), salt, dbPassword);
        user.setPassword(dbPassword);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setSalt(salt);
        user.setAvatar("https://c-ssl.duitang.com/uploads/blog/202207/03/20220703221307_2aeaf.jpg");
        context.setUser(user);

    }

    private void reg(UserContext context) throws NoSuchAlgorithmException {
        User user = context.getUser();
        if (ObjectUtil.isNotNull(user)) {
            try {
                if (userMapper.insert(user) != 1) {
                    throw new BizException("用户注册失败");
                }
            } catch (DuplicateKeyException e) {
                throw new BizException("用户名已存在");
            }
            log.info("用户注册成功");
            return;
        }
        throw new BizException(ResponseCode.ERROR);
    }

    private Long createUserRoot(UserContext userContext) {
        CreateFolderContext createFolderContext = new CreateFolderContext();
        createFolderContext.setUserId(userContext.getUser().getId());
        createFolderContext.setFolderName(CreateFolderConstants.ALL_FILE);
        createFolderContext.setParentId(CreateFolderConstants.TOP_PARENT_ID);
        //创建用户根目录
        Long userFileId = userFileService.createUserRoot(createFolderContext);
        return userFileId;
    }

    private void checkLoginInfo(UserLoginContext context) throws NoSuchAlgorithmException {
        MyBatisWrapper<User> wrapper = new MyBatisWrapper<>();
        wrapper.select(UserField.Avatar, UserField.Id, UserField.Username, UserField.Salt, UserField.Password).whereBuilder().andEq(UserField.setUsername(context.getUsername()));
        User user = userMapper.topOne(wrapper);
        if (ObjectUtil.isNotNull(user)) {
            String s = PasswordUtil.encryptPassword(context.getPassword(), user.getSalt());
            log.info("lOGIN-{},{},{}", context.getPassword(), user.getSalt(), s);
            if (s.equals(user.getPassword())) {
                User user1 = new User();
                user1.setId(user.getId());
                user1.setAvatar(user.getAvatar());
                user1.setUsername(user.getUsername());
                context.setUser(user1);
            } else {
                throw new BizException("密码错误");
            }
        } else {
            throw new BizException("用户名不存在");
        }
    }

    private void generateToken(UserLoginContext context) {
        User user = context.getUser();
        String token = JwtUtil.creatToken(user.getUsername(), user.getId(), 120);
        Cache cache = cacheManager.getCache(CacheConstants.YU_PAN_CACHE);
        cache.put(UserConstants.USER_LOGIN_PREFIX + user.getId(), token);
        context.setAccessToken(token);
    }

    public String checkAnswer(CheckAnswerContext context) {
        if (check(context)) {
            generateAndSaveToken(context);
            return context.getToken();
        }
        return null;
    }

    private void generateAndSaveToken(CheckAnswerContext context) {
        String token = JwtUtil.creatToken(context.getUsername(), context.getId(), 1);
        context.setToken(token);
    }

    private Boolean check(CheckAnswerContext context) {
        MyBatisWrapper<User> wrapper = new MyBatisWrapper<>();
        wrapper.select(UserField.Id).whereBuilder().andEq(UserField.setUsername(context.getUsername())).andEq(UserField.setAnswer(context.getAnswer())).andEq(UserField.setQuestion(context.getQuestion()));
        Integer count = userMapper.count(wrapper);
        if (count == 0) {
            throw new BizException("答案错误");
        }
        context.setId(count.longValue());
        return true;
    }

    public void resetPassword(ResetPasswordContext resetPasswordContext) {
        checkToken(resetPasswordContext);
        resetNewPassword(resetPasswordContext);
    }

    private void resetNewPassword(ResetPasswordContext resetPasswordContext) {
        String username = resetPasswordContext.getUsername();
        MyBatisWrapper<User> wrapper = new MyBatisWrapper<>();
        wrapper.select(UserField.Id, UserField.Salt).whereBuilder().andEq(UserField.setUsername(username));
        User user = userMapper.topOne(wrapper);
        if (ObjectUtil.isNull(user)) {
            throw new BizException(ResponseCode.USER_NOT_EXIST);
        }
        try {
            user.setPassword(PasswordUtil.encryptPassword(resetPasswordContext.getNewPassword(), user.getSalt()));
        } catch (NoSuchAlgorithmException e) {
            throw new BizException("密码加密失败");
        }
        if (userMapper.updateByPrimaryKeySelective(user) == -1) {
            throw new BizException("重置密码失败");
        }
    }

    private void checkToken(ResetPasswordContext resetPasswordContext) {
        String token = resetPasswordContext.getToken();
        if (!JwtUtil.validateToken(token)) {
            throw new BizException(ResponseCode.TOKEN_EXPIRED);
        }
        User user = BeanUtil.toBean(JwtUtil.getMember(token), User.class);
        if (!StrUtil.equals(user.getUsername(), resetPasswordContext.getUsername())) {
            throw new BizException("token异常");
        }
    }

    public void changePassword(ChangePasswordContext context) throws NoSuchAlgorithmException {
        checkOldPassword(context);
        setNewPassword(context);
        exit(context.getUserId());
    }

    private void checkOldPassword(ChangePasswordContext context) {
        String oldPassword = context.getPassword();
        MyBatisWrapper<User> wrapper = new MyBatisWrapper<>();
        wrapper.select(UserField.Id, UserField.Password, UserField.Salt).whereBuilder().andEq(UserField.setId(context.getUserId()));
        User user = userMapper.topOne(wrapper);
        if (ObjectUtil.isNull(user)) {

            throw new BizException(ResponseCode.USER_NOT_EXIST);
        }
        try {
            if (!ObjectUtil.equals(user.getPassword(), PasswordUtil.encryptPassword(oldPassword, user.getSalt()))) {
                throw new BizException("原密码错误");
            } else {
                context.setUser(user);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BizException("密码加密失败");
        }

    }

    private void setNewPassword(ChangePasswordContext context) throws NoSuchAlgorithmException {
        User user = context.getUser();
        user.setPassword(PasswordUtil.encryptPassword(context.getNewPassword(), user.getSalt()));
        userMapper.updateByPrimaryKeySelective(user);
    }
}
