package io.bms.bmswk.controller;

import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.model.support.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return R.ok().setData(String.format("Blackmesa Warehouse Keeper Backend API system. API Doc: %s/swagger-ui.html", baseUrl));
    }

}
