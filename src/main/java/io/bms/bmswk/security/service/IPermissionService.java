package io.bms.bmswk.security.service;

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

    /**
     * get permission with role name
     * @param roleName role name string
     * @return roleDto list
     */
    List<PermissionDTO> getPermissionListByRoleName(String roleName);

    /**
     * .
     * @param roleId role id int
     * @return roleDto list
     */
    List<PermissionDTO> getPermissionListByRoleId(Integer roleId);

}
