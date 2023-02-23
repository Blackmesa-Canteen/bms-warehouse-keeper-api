package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.param.UserRegisterParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    IUserService userService;
    public R create(@RequestBody UserRegisterParam param) {
        return R.ok();
    }

}
