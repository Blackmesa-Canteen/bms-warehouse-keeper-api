package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.exception.NotImplementedException;
import io.bms.bmswk.model.dto.InventoryItemDTO;
import io.bms.bmswk.model.entity.City;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.model.entity.WarehouseSku;
import io.bms.bmswk.model.param.AddStockParam;
import io.bms.bmswk.model.param.DeductStockParam;
import io.bms.bmswk.model.param.WarehouseCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.model.vo.WarehouseVO;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.*;
import io.bms.bmswk.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/api/v1/warehouse")
@Api(tags = "Warehouse management APIs")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    ICityService cityService;

    @Autowired
    private IWarehouseSkuService warehouseSkuService;

    @Autowired
    private ISkuService skuService;

    @Autowired
    private ISpuService spuService;

    /**
     * create new warehouse
     * @param param
     * @return R
     */
    @PostMapping("")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation("Create new warehouse")
    public R createNewWarehouse(@RequestBody @Valid WarehouseCreateParam param) {
        warehouseService.createWareHouse(param.getName(), param.getAddress(), param.getCityId());
        return R.ok();
    }

    /**
     * find warehouse by id
     */
    @GetMapping("/{warehouseId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Get warehouse info by id")
    public R getWarehouseById(@PathVariable String warehouseId) {
        Warehouse warehouse = warehouseService.getById(Integer.parseInt(warehouseId));
        if (warehouse == null) {
            return R.ok();
        }

        City city = cityService.getById(warehouse.getCityId());
        WarehouseVO warehouseVO = new WarehouseVO();
        warehouseVO.setWarehouseName(warehouse.getName());
        warehouseVO.setWarehouseId(warehouse.getId());
        warehouseVO.setAddress(warehouse.getAddress());

        if (city != null) {
            warehouseVO.setCityId(city.getId());
            warehouseVO.setCityName(city.getName());
        }

        return R.ok().setData(warehouseVO);
    }

    /**
     * find all warehouses by page
     * @param page page no
     * @param size page size
     * @return R
     */
    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Get all warehouse info by page")
    public R getWarehousesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Warehouse> thePage = warehouseService.page(new Page<>(page, size));

        List<Warehouse> records = thePage.getRecords();
        List<WarehouseVO> warehouseVOList = new LinkedList<>();
        records.stream().forEach(warehouse -> {
            City city = cityService.getById(warehouse.getCityId());
            WarehouseVO warehouseVO = new WarehouseVO();
            warehouseVO.setWarehouseName(warehouse.getName());
            warehouseVO.setWarehouseId(warehouse.getId());
            warehouseVO.setAddress(warehouse.getAddress());

            if (city != null) {
                warehouseVO.setCityId(city.getId());
                warehouseVO.setCityName(city.getName());
            }

            warehouseVOList.add(warehouseVO);
        });
        return R.ok().setData(warehouseVOList);
    }

    /**
     * get inventory items by warehouse Id
     * @param page page no
     * @param size page size
     * @param warehouseId target warehouse Id
     * @return list of items
     */
    @GetMapping("/items/query")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Get stocking product info by warehouse by page.")
    public R getInventoryDetailsByWarehouseIdByPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "warehouseId") Integer warehouseId) {

        List<InventoryItemDTO> itemDTOList = warehouseService.listWarehouseInventoryByIdByPage(
                page,
                size,
                warehouseId
        );

        return R.ok().setData(itemDTOList);
    }

    @PostMapping("/items/add")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation("Add a sku to warehouse.")
    public R addProductInWarehouse(@RequestBody @Valid AddStockParam param) {
        warehouseSkuService.addSkuInWarehouse(param.getWarehouseId(), param.getSkuId(), param.getNum(), param.getUnit());

        return R.ok();
    }

    @PostMapping("/items/deduct")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation("Deduct a sku from warehouse.")
    public R deductProductInWarehouse(@RequestBody @Valid DeductStockParam param) {
        warehouseSkuService.deductSkuInWareHouse(param.getWarehouseId(), param.getSkuId(), param.getNum());

        return R.ok();
    }

    @GetMapping("/query")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("[Not Implemented] query warehouse")
    public R queryWarehousesByPage(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "cityId") Integer cityId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {

        if (StringUtils.isEmpty(name) && cityId == null) {
            // no conditions, retual all
            return getWarehousesByPage(page, size);
        }

        if (cityId == null) {
            // query name
            throw new NotImplementedException();
        } else if (name == null) {
            // query cityId
            throw new NotImplementedException();
        } else {
            // query two factors
            throw new NotImplementedException();
        }
    }
}
