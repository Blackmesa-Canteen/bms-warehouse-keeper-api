package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Purchase;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.model.param.PurchaseCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.model.vo.PurchaseVO;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.IPurchaseService;
import io.bms.bmswk.service.ISkuService;
import io.bms.bmswk.service.IUserService;
import io.bms.bmswk.service.IWarehouseService;
import io.bms.bmswk.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@Validated
@RequestMapping("/api/v1/purchase")
@Api(tags = "Purchase/Stock-In operation APIs")
public class PurchaseController {

    private final IPurchaseService purchaseService;

    private final ISkuService skuService;

    private final IWarehouseService warehouseService;

    private final IUserService userService;

    public PurchaseController(IPurchaseService purchaseService, ISkuService skuService, IWarehouseService warehouseService, IUserService userService) {
        this.purchaseService = purchaseService;
        this.skuService = skuService;
        this.warehouseService = warehouseService;
        this.userService = userService;
    }

    @GetMapping("/{purchaseId}")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_PURCHASE_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR)
    @ApiOperation("get purchase request by id")
    public R getPurchaseById(@PathVariable Integer purchaseId) {
        Purchase thePurchase = purchaseService.getById(purchaseId);
        PurchaseVO purchaseVO = BeanUtils.transformFrom(thePurchase, PurchaseVO.class);
        Sku theSku = skuService.getById(thePurchase.getSkuId());
        Warehouse theWarehouse = warehouseService.getById(thePurchase.getWarehouseId());
        User purchaser = userService.getById(thePurchase.getPurchaserId());
        User keeper;
        if (thePurchase.getKeeperId() != null) {
            keeper = userService.getById(thePurchase.getKeeperId());
            purchaseVO.setKeeperName(keeper.getName());
        }

        purchaseVO.setSkuName(theSku.getName());
        purchaseVO.setWarehouseName(theWarehouse.getName());
        purchaseVO.setPurchaserName(purchaser.getName());
        purchaseVO.setPurchaseId(purchaseId);

        return R.ok().setData(purchaseVO);
    }

    @GetMapping("/all")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_PURCHASE_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR
    )
    @ApiOperation("query purchase request by page")
    public R getPurchasesByPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "status") Byte status) {

        if (status == null) {
            Page<Purchase> thePage = purchaseService.page(new Page<>(page, size));
            List<PurchaseVO> purchaseVOList = genPurchaseVOListByPageQuery(thePage);
            return R.ok().setData(purchaseVOList);
        } else {
            QueryWrapper<Purchase> wrapper = new QueryWrapper<>();
            wrapper.eq("status", status);
            Page<Purchase> thePage = purchaseService.page(
                    new Page<>(page, size), wrapper
            );
            List<PurchaseVO> purchaseVOList = genPurchaseVOListByPageQuery(thePage);
            return R.ok().setData(purchaseVOList);
        }
    }

    @PostMapping("")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_PURCHASE_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR)
    @ApiOperation(value = "create new purchase/In-stock request",
            notes = "The new request will pend for auditing")
    public R createOnePurchase(@RequestBody @Valid PurchaseCreateParam param) {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        purchaseService.createOnePurchaseOrder(
                param.getSkuId(),
                param.getWarehouseId(),
                userId,
                param.getNum(),
                param.getPrice()
        );

        return R.ok();
    }

    @GetMapping("/audit")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation(value = "audit a purchase request",
            notes = "If pass, perform adding items in warehouse. Otherwise, mark the request as rejected.")
    public R auditOnePurchaseRequest(@RequestParam(value = "isConfirmed") Boolean isConfirmed,
                                     @RequestParam(value = "purchaseId") Integer purchaseId) {
        Integer keeperId = (Integer) SecurityUtils.getSubject().getPrincipal();

        purchaseService.auditPurchaseOrderStatus(
                purchaseId,
                keeperId,
                isConfirmed
        );

        return R.ok();
    }

    /**
     * generate vo list
     *
     * @param thePage page query result
     * @return list of vos
     */
    private List<PurchaseVO> genPurchaseVOListByPageQuery(Page<Purchase> thePage) {
        List<PurchaseVO> purchaseVOList = new LinkedList<>();
        thePage.getRecords().forEach(thePurchase -> {
            PurchaseVO purchaseVO = BeanUtils.transformFrom(thePurchase, PurchaseVO.class);
            Sku theSku = skuService.getById(thePurchase.getSkuId());
            Warehouse theWarehouse = warehouseService.getById(thePurchase.getWarehouseId());
            User purchaser = userService.getById(thePurchase.getPurchaserId());
            User keeper;
            if (thePurchase.getKeeperId() != null) {
                keeper = userService.getById(thePurchase.getKeeperId());
                purchaseVO.setKeeperName(keeper.getName());
            }

            purchaseVO.setSkuName(theSku.getName());
            purchaseVO.setWarehouseName(theWarehouse.getName());
            purchaseVO.setPurchaserName(purchaser.getName());
            purchaseVO.setPurchaseId(thePurchase.getId());

            purchaseVOList.add(purchaseVO);
        });
        return purchaseVOList;
    }
}
