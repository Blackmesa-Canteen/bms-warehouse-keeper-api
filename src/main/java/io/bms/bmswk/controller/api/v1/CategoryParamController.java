package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.CategoryParam;
import io.bms.bmswk.model.param.CategoryParamCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.ICategoryParamService;
import io.bms.bmswk.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@Api(tags = "Category parameters/attributes related api")
public class CategoryParamController {

    @Autowired
    private ICategoryParamService categoryParamService;

    @GetMapping("/{categoryParamId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("query category param by id")
    public R getCategoryParamById(@PathVariable String categoryParamId) {
        return R.ok().setData(categoryParamService.getById(Integer.parseInt(categoryParamId)));
    }

    @GetMapping("/query")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("query category params by category id")
    public R getCategoryParamByCategoryId(@RequestParam(value = "categoryId") Integer categoryId) {
        List<CategoryParam> res = categoryParamService.getAllParamsByCategoryId(categoryId);
        return R.ok().setData(res);
    }

    @DeleteMapping("/{categoryParamId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("delete category param by id")
    public R deleteCategoryParam(@PathVariable String categoryParamId) {
        categoryParamService.removeById(Integer.parseInt(categoryParamId));
        return R.ok();
    }

    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("get all params by page")
    public R getCategoryParamsByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<CategoryParam> thePage = categoryParamService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation("create one parameter config for category")
    public R createCategoryParam(@RequestBody @Valid CategoryParamCreateParam param) {
        CategoryParam categoryParam = BeanUtils.transformFrom(param, CategoryParam.class);

        categoryParamService.save(categoryParam);

        return R.ok();
    }

}
