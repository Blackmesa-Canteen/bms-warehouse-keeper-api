package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.support.R;
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

    @GetMapping("/role_name/{roleName}")
    public R findPermissionsByRoleName(@PathVariable String roleName) {

    }

}
