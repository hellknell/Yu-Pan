package com.Yu.Pan.service.domain;


import com.Yu.Pan.core.generator.help.DbField;
import com.Yu.Pan.core.generator.help.FieldResult;

import java.util.Collections;

public class UserField {
    public static DbField Id = new DbField("id","id","BIGINT","java.lang.Long");

    public static DbField Avatar = new DbField("avatar","avatar","VARCHAR","java.lang.String");

    public static DbField Username = new DbField("username","username","VARCHAR","java.lang.String");

    public static DbField Password = new DbField("password","password","VARCHAR","java.lang.String");

    public static DbField Salt = new DbField("salt","salt","VARCHAR","java.lang.String");

    public static DbField Question = new DbField("question","question","VARCHAR","java.lang.String");

    public static DbField Answer = new DbField("answer","answer","VARCHAR","java.lang.String");

    public static DbField CreateTime = new DbField("create_time","createTime","TIMESTAMP","java.util.Date");

    public static DbField UpdateTime = new DbField("update_time","updateTime","TIMESTAMP","java.util.Date");

    public static FieldResult setId(Long id) {
        return new FieldResult(Id, Collections.singletonList(id));
    }

    public static FieldResult setAvatar(String avatar) {
        return new FieldResult(Avatar, Collections.singletonList(avatar));
    }

    public static FieldResult setUsername(String username) {
        return new FieldResult(Username, Collections.singletonList(username));
    }

    public static FieldResult setPassword(String password) {
        return new FieldResult(Password, Collections.singletonList(password));
    }

    public static FieldResult setSalt(String salt) {
        return new FieldResult(Salt, Collections.singletonList(salt));
    }

    public static FieldResult setQuestion(String question) {
        return new FieldResult(Question, Collections.singletonList(question));
    }

    public static FieldResult setAnswer(String answer) {
        return new FieldResult(Answer, Collections.singletonList(answer));
    }

    public static FieldResult setCreateTime(java.util.Date createTime) {
        return new FieldResult(CreateTime, Collections.singletonList(createTime));
    }

    public static FieldResult setUpdateTime(java.util.Date updateTime) {
        return new FieldResult(UpdateTime, Collections.singletonList(updateTime));
    }
}