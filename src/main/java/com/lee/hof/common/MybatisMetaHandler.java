package com.lee.hof.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lee.hof.auth.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime",  Date.class, new Date());
        this.strictInsertFill(metaObject, "createBy", Long.class, UserContext.getUserId() == null ? 0L : UserContext.getUserId());
        this.strictInsertFill(metaObject, "updateBy", Long.class, UserContext.getUserId() == null ? 0L: UserContext.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime",  Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateBy", Long.class, UserContext.getUserId() == null ? 0L: UserContext.getUserId());
    }
}

