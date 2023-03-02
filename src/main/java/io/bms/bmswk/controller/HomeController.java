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

}
