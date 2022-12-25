package com.lee.hof.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lee.hof.auth.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MybatisMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createBy", Long.class, UserContext.getUserId() == null? 0L : UserContext.getUserId());
        this.strictInsertFill(metaObject, "updateBy", Long.class, UserContext.getUserId() == null ? 0L: UserContext.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateBy", Long.class, UserContext.getUserId() == null ? 0L: UserContext.getUserId());
    }
}

