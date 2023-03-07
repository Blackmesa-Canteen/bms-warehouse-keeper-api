package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.City;
import io.bms.bmswk.model.param.CityCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.ICityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/v1/city")
@Api(tags = "City related apis")
public class CityController {

    private final ICityService cityService;

    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("query city by id")
    public R getCityById(@PathVariable Integer id) {
        City city = cityService.getById(id);

        return R.ok().setData(city);
    }

    @DeleteMapping("/{id}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("delete city by id")
    public R deleteCityById(@PathVariable Integer id) {
        cityService.deleteCityById(id);

        return R.ok();
    }

    /**
     * get all city
     * @param page page num
     * @param size page size
     * @return
     */
    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("get all cities by page")
    public R getCitiesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<City> thePage = cityService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation("delete city by id")
    public R addCity(@RequestBody @Valid CityCreateParam param) {
        City city = new City();
        city.setName(param.getName());

        cityService.save(city);

        return R.ok();
    }

}
