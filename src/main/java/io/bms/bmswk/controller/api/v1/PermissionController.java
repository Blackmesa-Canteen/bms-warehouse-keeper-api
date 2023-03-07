package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.security.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/api/v1/permission")
@Api(tags = "Permission related APIs")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/{permissionId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Find permission by id")
    public R findPermissionByPermissionId(@PathVariable String permissionId) {
        return R.ok().setData(permissionService.getById(permissionId));
    }

    @GetMapping("/role_name/{roleName}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Find permissions by role name")
    public R findPermissionsByRoleName(@PathVariable String roleName) {
        return R.ok().setData(permissionService.getPermissionListByRoleName(roleName));
    }

    @GetMapping("/role_id/{roleId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Find permissions by role id")
    public R findPermissionsByRoleId(@PathVariable String roleId) {
        Integer roleIdInt = Integer.parseInt(roleId);
        return R.ok().setData(permissionService.getPermissionListByRoleId(roleIdInt));
    }

}
