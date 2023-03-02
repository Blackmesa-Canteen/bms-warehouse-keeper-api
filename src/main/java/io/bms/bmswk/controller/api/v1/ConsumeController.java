package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Consume;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.model.param.ConsumeCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.IConsumeService;
import io.bms.bmswk.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import springfox.documentation.spi.service.contexts.SecurityContext;

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
@RequestMapping("/api/v1/consume")
public class ConsumeController {

    @Autowired
    private IConsumeService consumeService;

    @GetMapping("/{consumeId}")
    public R getConsumeById(@PathVariable String consumeId) {
        return R.ok().setData(consumeService.getById(Integer.parseInt(consumeId)));
    }

    @GetMapping("/all")
    public R getConsumesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Consume> thePage = consumeService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    public R createConsumeOrder(@RequestBody @Valid ConsumeCreateParam param) {
        consumeService.createOneConsumeOrder(
                param.getSkuId(),
                param.getWarehouseId(),
                param.getConsumerId(),
                param.getNum()
        );

        return R.ok();
    }

    @GetMapping("/audit")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    public R toggleConsumerOrderStatus(@RequestParam(name = "status") Byte status,
                                       @RequestParam(name = "consumeId") Integer consumeId) {

        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();

        consumeService.toggleConsumeOrderStatus(
                consumeId,
                status,
                userId
        );

        return R.ok();
    }

}
