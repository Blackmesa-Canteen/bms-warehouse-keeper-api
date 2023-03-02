package io.bms.bmswk.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * handles meta data for db entity
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 14:13
 */
@Component
public class EntityMetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "dtCreated", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "dtUpdated", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "valid", Boolean.class, true);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "dtUpdated", LocalDateTime.class, LocalDateTime.now());
    }
}
