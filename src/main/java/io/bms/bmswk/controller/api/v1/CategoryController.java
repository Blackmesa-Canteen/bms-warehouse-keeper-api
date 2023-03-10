package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.exception.NotImplementedException;
import io.bms.bmswk.model.dto.CategoryParamDTO;
import io.bms.bmswk.model.entity.Category;
import io.bms.bmswk.model.entity.CategoryParam;
import io.bms.bmswk.model.param.CategoryCreateParam;
import io.bms.bmswk.model.param.CategoryParamCreateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.ICategoryParamService;
import io.bms.bmswk.service.ICategoryService;
import io.bms.bmswk.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * item category table controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@Validated
@RequestMapping("/api/v1/category")
@Api(tags = "Category Related")
public class CategoryController {

    private final ICategoryService categoryService;

    private final ICategoryParamService categoryParamService;

    public CategoryController(ICategoryService categoryService, ICategoryParamService categoryParamService) {
        this.categoryService = categoryService;
        this.categoryParamService = categoryParamService;
    }

    @GetMapping("/{categoryId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("query category by id")
    public R getCategoryById(@PathVariable String categoryId) {
        return R.ok().setData(categoryService.getById(Integer.parseInt(categoryId)));
    }

    @DeleteMapping("/{categoryId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("delete category by id")
    public R deleteCategoryById(@PathVariable Integer categoryId) {
        categoryService.deleteCategoryById(categoryId);

        return R.ok();
    }

    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("get all category records by page")
    public R getCategoriesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Category> thePage = categoryService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation(value = "create a category",
            notes = "create categories and corresponding category parameter records.")
    public R createCategory(@RequestBody @Valid CategoryCreateParam param) {

        // check duplicate
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("name", param.getName());

        synchronized (this) {
            Category one = categoryService.getOne(wrapper);
            if (one != null) {
                return R.error(ExceptionCodeEnum.REQUEST_GENERIC_EXCEPTION.getCode(),
                        "Category name already exists");
            }

            Category category = BeanUtils.transformFrom(param, Category.class);
            if (category != null) {
                category.setValid(true);
            }
            // save category
            categoryService.save(category);
            // get the new one
            Category newOne = categoryService.getOne(wrapper);

            //
            // insert category params batch
            List<CategoryParamDTO> params = param.getParams();
            List<CategoryParam> paramEntities = new LinkedList<>();
            for (CategoryParamDTO obj : params) {
                CategoryParam categoryParam = BeanUtils.transformFrom(obj, CategoryParam.class);
                categoryParam.setCategoryId(newOne.getId());
                paramEntities.add(categoryParam);
            }

            // save params
            categoryParamService.saveBatch(paramEntities);



            return R.ok();
        }
    }
}
