package io.bms.bmswk.security.service.impl;

import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.exception.InternalException;
import io.bms.bmswk.model.dto.PermissionDTO;
import io.bms.bmswk.model.entity.Permission;
import io.bms.bmswk.mapper.PermissionMapper;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bms.bmswk.util.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    private static final Logger LOGGER = LogManager.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper mapper;

    @Override
    public List<PermissionDTO> getPermissionListByRoleName(String roleName) {
        try {
            return mapper.getPermissionsByRoleName(roleName);

        } catch (Exception e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            throw new InternalException(ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode(),
                    "error in get permissions by role name");
        }
    }

    @Override
    public List<PermissionDTO> getPermissionListByRoleId(Integer roleId) {
        try {
            return mapper.getPermissionsByRoleId(roleId);
        } catch (Exception e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            throw new InternalException(ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode(),
                    "error in get permissions by role id");
        }
    }
}
