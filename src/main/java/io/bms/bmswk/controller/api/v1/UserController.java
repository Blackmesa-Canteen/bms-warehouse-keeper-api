package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.constant.CommonConstant;
import io.bms.bmswk.model.entity.Role;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.model.param.RefreshTokenParam;
import io.bms.bmswk.model.param.UserLoginParam;
import io.bms.bmswk.model.param.UserRegisterParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.model.vo.UserVO;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.security.service.IAuthService;
import io.bms.bmswk.security.service.IRoleService;
import io.bms.bmswk.service.IUserService;
import io.bms.bmswk.util.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    IAuthService authService;

    @Autowired
    IRoleService roleService;

    /**
     * register user
     * @param param register request body
     * @return response
     */
    @PostMapping("")
    @RequiresPermissions(SecurityConstant.USER_MANAGE_PERMISSION)
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
     * login and get auth token
     * @param param UserLoginParam
     * @return token string
     */
    @PostMapping("/login")
    public R login(@RequestBody @Valid UserLoginParam param) {
        String token = authService.loginUser(param.getLoginId(), param.getPassword());
        return R.ok().setData(token);
    }

    /**
     * refresh auth token
     * @param param
     * @return
     */
    @PostMapping("/refreshToken")
    public R login(@RequestBody @Valid RefreshTokenParam param) {
        String token = authService.refreshTokenStr(param.getToken());
        return R.ok().setData(token);
    }

    /**
     * get user by primary key
     */
    @GetMapping("/{id}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    public R getUserById(@PathVariable String id) {
        // user vo ignores sensitive information: password
        User user = userService.getById(id);
        Role role = roleService.getById(user.getRoleId());
        UserVO userVO = BeanUtils.transformFrom(user, UserVO.class);
        if (userVO != null) {
            userVO.setRoleName(role.getName());
        }
        return R.ok().setData(userVO);
    }

    @DeleteMapping("/{id}")
    @RequiresPermissions({SecurityConstant.USER_MANAGE_PERMISSION})
    public R deleteUserById(@PathVariable String id) {
        userService.removeById(id);
        return R.ok();
    }

    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    public R getAllUserByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<User> thePage = userService.page(new Page<>(page, size));
        List<UserVO> userVOList = new LinkedList<>();
        thePage.getRecords().forEach(user -> {
            UserVO userVO = BeanUtils.transformFrom(user, UserVO.class);

            Role role = roleService.getById(user.getRoleId());
            if (userVO != null) {
                userVO.setRoleName(role.getName());
            }

            userVOList.add(userVO);
        });
        return R.ok().setData(userVOList);
    }

}
