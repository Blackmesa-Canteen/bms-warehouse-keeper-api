package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Consume;
import io.bms.bmswk.model.param.ConsumeCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.IConsumeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_CONSUME_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR
    )
    public R getConsumeById(@PathVariable String consumeId) {
        return R.ok().setData(consumeService.getById(Integer.parseInt(consumeId)));
    }

    @GetMapping("/all")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_CONSUME_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR
    )
    public R getConsumesByPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "status") Byte status) {

        if (status == null) {
            Page<Consume> thePage = consumeService.page(
                    new Page<>(page, size)
            );
            return R.ok().setData(thePage.getRecords());
        }

        QueryWrapper<Consume> wrapper = new QueryWrapper<>();
        wrapper.eq("status", status);
        Page<Consume> thePage = consumeService.page(
                new Page<>(page, size), wrapper
        );

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_CONSUME_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR
    )
    public R createConsumeOrder(@RequestBody @Valid ConsumeCreateParam param) {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        consumeService.createOneConsumeOrder(
                param.getSkuId(),
                param.getWarehouseId(),
                userId,
                param.getNum()
        );

        return R.ok();
    }

    @GetMapping("/audit")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    public R auditOneConsumeRequest(@RequestParam(value = "isConfirmed") Boolean isConfirmed,
                                       @RequestParam(value = "consumeId") Integer consumeId) {

        Integer keeperId = (Integer) SecurityUtils.getSubject().getPrincipal();

        consumeService.auditConsumeOrderStatus(
                consumeId,
                keeperId,
                isConfirmed
        );

        return R.ok();
    }

}
