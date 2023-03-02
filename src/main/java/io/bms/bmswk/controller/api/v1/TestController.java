package io.bms.bmswk.controller.api.v1;

import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-03 10:32
 */
@RestController("/api/v1/test")
public class TestController {

    @GetMapping("/purchase")
    @RequiresPermissions({SecurityConstant.INVENTORY_PURCHASE_PERMISSION})
    public R testPurchasePermission() {
        return R.ok().setData((String) SecurityUtils.getSubject().getPrincipal());
    }

    @GetMapping("/consume")
    @RequiresPermissions({SecurityConstant.INVENTORY_CONSUME_PERMISSION})
    public R testConsumePermission() {
        return R.ok().setData((String) SecurityUtils.getSubject().getPrincipal());
    }

    @GetMapping("/user_manage")
    @RequiresPermissions({SecurityConstant.USER_MANAGE_PERMISSION})
    public R testUsermanagePermission() {
        return R.ok().setData((String) SecurityUtils.getSubject().getPrincipal());
    }
}
