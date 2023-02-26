package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Controller
@RequestMapping("/api/v1/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/role_name/{roleName}")
    public R findPermissionsByRoleName(@PathVariable String roleName) {
        return permissionService.getPermissionListByRoleName(roleName);
    }

    @GetMapping("/role_name/{roleId}")
    public R findPermissionsByRoleId(@PathVariable String roleId) {
        Integer roleIdInt = Integer.parseInt(roleId);
        return permissionService.getPermissionListByRoleId(roleIdInt);
    }

}
