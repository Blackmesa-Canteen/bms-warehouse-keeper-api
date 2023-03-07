package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.entity.Role;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.security.service.IRoleService;
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
@RequestMapping("/api/v1/role")
@Api(tags = "Role APIs")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{roleId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION, SecurityConstant.USER_MANAGE_PERMISSION})
    @ApiOperation("get role info by id")
    public R getRoleById(@PathVariable String roleId) {
        Role role = roleService.getById(Integer.parseInt(roleId));
        return R.ok().setData(role);
    }

}
