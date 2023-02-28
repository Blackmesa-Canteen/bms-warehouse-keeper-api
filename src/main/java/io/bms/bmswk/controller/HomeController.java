package io.bms.bmswk.controller;

import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.model.support.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * index Restful server
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:56
 */

@RestController
public class HomeController {

    @GetMapping("/")
    public R index() {
        return R.ok().setData("Blackmesa Warehouse Keeper Backend API system.");
    }

//    @GetMapping("/403")
//    public R forbidden() {
//        return R.error(
//                ExceptionCodeEnum.NO_PERMISSION_EXCEPTION.getCode(),
//                ExceptionCodeEnum.NO_PERMISSION_EXCEPTION.getMessage()
//                );
//    }
//
//    @GetMapping("/401")
//    public R authFailed() {
//        return R.error(
//                ExceptionCodeEnum.AUTH_EXCEPTION.getCode(),
//                ExceptionCodeEnum.AUTH_EXCEPTION.getMessage()
//        );
//    }
}
