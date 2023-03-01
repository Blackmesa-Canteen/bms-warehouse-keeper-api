package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.CategoryParam;
import io.bms.bmswk.model.param.CategoryCreateParam;
import io.bms.bmswk.model.param.CategoryParamCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.ICategoryParamService;
import io.bms.bmswk.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 * parameter table for item categories controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@Validated
@RequestMapping("/api/v1/categoryParam")
public class CategoryParamController {

    @Autowired
    private ICategoryParamService categoryParamService;

    @GetMapping("/{categoryParamId}")
    public R getCategoryParamById(@PathVariable String categoryParamId) {
        return R.ok().setData(categoryParamService.getById(Integer.parseInt(categoryParamId)));
    }

    @DeleteMapping("/{categoryParamId}")
    public R deleteCategoryParam(@PathVariable String categoryParamId) {
        categoryParamService.removeById(Integer.parseInt(categoryParamId));
        return R.ok();
    }

    @GetMapping("/all")
    public R getCategoryParamsByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<CategoryParam> thePage = categoryParamService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    public R createCategoryParam(@RequestBody @Valid CategoryParamCreateParam param) {
        CategoryParam categoryParam = BeanUtils.transformFrom(param, CategoryParam.class);

        categoryParamService.save(categoryParam);

        return R.ok();
    }

}
