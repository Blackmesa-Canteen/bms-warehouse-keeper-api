package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.City;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/{id}")
    public R getCityById(@PathVariable Integer id) {
        City city = cityService.getById(id);

        return R.ok().setData(city);
    }

    @GetMapping("/all")
    public R getCitiesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<City> thePage = cityService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

}
