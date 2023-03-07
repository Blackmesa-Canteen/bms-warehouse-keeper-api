package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Consume;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.model.param.ConsumeCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.model.vo.ConsumeVO;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.IConsumeService;
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
 *  controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@Validated
@RequestMapping("/api/v1/consume")
@Api(tags = "Consume/Stock-Out operation APIs")
public class ConsumeController {

    @Autowired
    private IConsumeService consumeService;

    @Autowired
    private ISkuService skuService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IUserService userService;

    @GetMapping("/{consumeId}")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_CONSUME_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR
    )
    @ApiOperation("query consume request by id")
    public R getConsumeById(@PathVariable Integer consumeId) {
        Consume theConsume = consumeService.getById(consumeId);
        ConsumeVO consumeVO = BeanUtils.transformFrom(theConsume, ConsumeVO.class);
        Sku theSku = skuService.getById(theConsume.getSkuId());
        Warehouse theWarehouse = warehouseService.getById(theConsume.getWarehouseId());
        User consumer = userService.getById(theConsume.getConsumerId());
        User keeper;

        if (theConsume.getKeeperId() != null) {
            keeper = userService.getById(theConsume.getKeeperId());
            consumeVO.setKeeperName(keeper.getName());
        }

        consumeVO.setSkuName(theSku.getName());
        consumeVO.setWarehouseName(theWarehouse.getName());
        consumeVO.setConsumerName(consumer.getName());
        consumeVO.setConsumeId(consumeId);
        return R.ok().setData(consumeVO);
    }

    @GetMapping("/all")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_CONSUME_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR
    )
    @ApiOperation("query consume requests by page")
    public R getConsumesByPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "status") Byte status) {

        if (status == null) {
            Page<Consume> thePage = consumeService.page(
                    new Page<>(page, size)
            );

            List<ConsumeVO> consumeVOList = genConsumeVOListByPage(thePage);
            return R.ok().setData(consumeVOList);
        }

        QueryWrapper<Consume> wrapper = new QueryWrapper<>();
        wrapper.eq("status", status);
        Page<Consume> thePage = consumeService.page(
                new Page<>(page, size), wrapper
        );

        List<ConsumeVO> consumeVOList = genConsumeVOListByPage(thePage);
        return R.ok().setData(consumeVOList);
    }

    @PostMapping("")
    @RequiresPermissions(
            value = {SecurityConstant.INVENTORY_CONSUME_PERMISSION, SecurityConstant.INVENTORY_MANAGE_PERMISSION},
            logical = Logical.OR
    )
    @ApiOperation(value = "create consume/out-stock request",
            notes = "The consume request will wait/pend for audit by warehouse keeper.")
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
    @ApiOperation(value = "audit consume/out-stock request",
            notes = "If pass, perform the operation, deduct items in warehouse. If reject, register the request to rejected.")
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

    /**
     * gen vos
     * @param thePage page query result
     * @return vos
     */
    private List<ConsumeVO> genConsumeVOListByPage(Page<Consume> thePage) {
        List<ConsumeVO> consumeVOList = new LinkedList<>();

        thePage.getRecords().forEach(theConsume -> {
            ConsumeVO consumeVO = BeanUtils.transformFrom(theConsume, ConsumeVO.class);
            Sku theSku = skuService.getById(theConsume.getSkuId());
            Warehouse theWarehouse = warehouseService.getById(theConsume.getWarehouseId());
            User consumer = userService.getById(theConsume.getConsumerId());
            User keeper;

            if (theConsume.getKeeperId() != null) {
                keeper = userService.getById(theConsume.getKeeperId());
                consumeVO.setKeeperName(keeper.getName());
            }

            consumeVO.setSkuName(theSku.getName());
            consumeVO.setWarehouseName(theWarehouse.getName());
            consumeVO.setConsumerName(consumer.getName());
            consumeVO.setConsumeId(theConsume.getId());

            consumeVOList.add(consumeVO);
        });
        return consumeVOList;
    }
}
