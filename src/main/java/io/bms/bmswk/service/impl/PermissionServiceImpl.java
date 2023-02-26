package io.bms.bmswk.service.impl;

import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.model.dto.PermissionDTO;
import io.bms.bmswk.model.entity.Permission;
import io.bms.bmswk.mapper.PermissionMapper;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bms.bmswk.util.ExceptionUtils;
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

    @Autowired
    private PermissionMapper mapper;

    @Override
    public R getPermissionListByRoleName(String roleName) {
        try {
            List<PermissionDTO> res = mapper.getPermissionsByRoleName(roleName);
            return R.ok().setData(res);

        } catch (Exception e) {
            return R.error(ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode(),
                    "error in get permissions by role name").setData(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public R getPermissionListByRoleId(Integer roleId) {
        try {
            List<PermissionDTO> res = mapper.getPermissionsByRoleId(roleId);
            return R.ok().setData(res);

        } catch (Exception e) {
            return R.error(ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode(),
                    "error in get permissions by role name").setData(ExceptionUtils.getStackTrace(e));
        }
    }
}
