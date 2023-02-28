package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.model.param.UserRegisterParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.model.vo.UserVO;
import io.bms.bmswk.service.IUserService;
import io.bms.bmswk.util.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * register user
     * @param param register request body
     * @return response
     */
    @PostMapping("")
    public R create(@RequestBody @Valid UserRegisterParam param) {
        userService.registerUser(
                param.getLoginId(),
                param.getName(),
                param.getPassword(),
                param.getRoleId(),
                param.getPhone()
        );

        return R.ok();
    }

    /**
     * get user by primary key
     */
    @GetMapping("/{id}")
    @RequiresAuthentication
    @RequiresPermissions("user_manage")
    public R getUserById(@PathVariable String id) {
        // TODO update user VO
        // user vo ignores sensitive information
        User user = userService.getById(id);
        UserVO userVO = BeanUtils.transformFrom(user, UserVO.class);
        return R.ok().setData(userVO);
    }

}
