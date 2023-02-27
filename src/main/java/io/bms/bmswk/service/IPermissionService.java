package io.bms.bmswk.service;

import io.bms.bmswk.model.dto.PermissionDTO;
import io.bms.bmswk.model.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import io.bms.bmswk.model.support.R;

import java.util.List;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface IPermissionService extends IService<Permission> {

    R getPermissionListByRoleName(String roleName);

    R getPermissionListByRoleId(Integer roleId);

}
