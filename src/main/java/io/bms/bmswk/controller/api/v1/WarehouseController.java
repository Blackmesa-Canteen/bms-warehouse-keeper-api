package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.exception.NotImplementedException;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.model.param.WarehouseCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IWarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Controller
@Validated
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    /**
     * create new warehouse
     * @param param
     * @return R
     */
    @PostMapping("")
    public R createNewWarehouse(@RequestBody @Valid WarehouseCreateParam param) {
        warehouseService.createWareHouse(param.getName(), param.getAddress(), param.getCityId());
        return R.ok();
    }

    /**
     * find warehouse by id
     */
    @GetMapping("/{warehouseId}")
    public R getWarehouseById(@PathVariable String warehouseId) {
        Warehouse warehouse = warehouseService.getById(Integer.parseInt(warehouseId));

        return R.ok().setData(warehouse);
    }

    /**
     * find all warehouses by page
     * @param page page no
     * @param size page size
     * @return R
     */
    @GetMapping("/all")
    public R getWarehousesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Warehouse> thePage = warehouseService.page(new Page<>(page, size));
        return R.ok().setData(thePage.getRecords());
    }

    @GetMapping("/query")
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
