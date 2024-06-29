package com.Yu.Pan.service.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.Yu.Pan.core.exception.BizException;
import com.Yu.Pan.core.response.ResponseCode;
import com.Yu.Pan.core.utils.IdUtil;
import com.Yu.Pan.core.utils.PasswordUtil;
import com.Yu.Pan.service.constants.CreateFolderConstants;
import com.Yu.Pan.service.context.CreateFolderContext;
import com.Yu.Pan.service.context.UserContext;
import com.Yu.Pan.service.converter.UserConverter;
import com.Yu.Pan.service.domain.User;
import com.Yu.Pan.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private void assemblyUserContext(UserContext context) throws NoSuchAlgorithmException {
        User user = UserConverter.INSTANCE.toUser(context);
        DateTime now = DateTime.now();
        user.setId(IdUtil.getSnowFlaskId());
        String salt = PasswordUtil.genSalt(), dbPassword = PasswordUtil.encryptPassword(user.getPassword(), salt);
        user.setPassword(dbPassword);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setSalt(salt);
        user.setAvatar("https://c-ssl.duitang.com/uploads/blog/202207/03/20220703221307_2aeaf.jpg");
        context.setUser(user);

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
            return;
        }
        throw new BizException(ResponseCode.ERROR);
    }
}
