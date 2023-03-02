package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Purchase;
import io.bms.bmswk.model.param.PurchaseCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.IPurchaseService;
import org.apache.shiro.SecurityUtils;
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
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @GetMapping("/{purchaseId}")
    public R getPurchaseById(@PathVariable String purchaseId) {
        return R.ok().setData(purchaseService.getById(Integer.parseInt(purchaseId)));
    }

    @GetMapping("/all")
    public R getPurchasesByPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "status") Byte status) {

        if (status == null) {
            Page<Purchase> thePage = purchaseService.page(new Page<>(page, size));
            return R.ok().setData(thePage.getRecords());
        } else {
            QueryWrapper<Purchase> wrapper = new QueryWrapper<>();
            wrapper.eq("status", status);
            Page<Purchase> thePage = purchaseService.page(
                    new Page<>(page, size), wrapper
            );
            return R.ok().setData(thePage.getRecords());
        }
    }

    @PostMapping("")
    public R createOnePurchase(@RequestBody @Valid PurchaseCreateParam param) {
        purchaseService.createOnePurchaseOrder(
                param.getSkuId(),
                param.getWarehouseId(),
                param.getPurchaserId(),
                param.getNum(),
                param.getPrice()
        );

        return R.ok();
    }

    @GetMapping("/audit")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    public R auditOnePurchaseRequest(@RequestParam(name = "isConfirmed") Boolean isConfirmed,
                                     @RequestParam(name = "purchaseId") Integer purchaseId) {
        Integer keeperId = (Integer) SecurityUtils.getSubject().getPrincipal();

        purchaseService.auditPurchaseOrderStatus(
                purchaseId,
                keeperId,
                isConfirmed
        );

        return R.ok();
    }

}
