package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.entity.Role;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.service.IRoleService;
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
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/{roleId}")
    public R getRoleById(@PathVariable String roleId) {
        Role role = roleService.getById(Integer.parseInt(roleId));
        return R.ok().setData(role);
    }

}
