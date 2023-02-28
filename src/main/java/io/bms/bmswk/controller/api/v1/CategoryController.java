package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Category;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * item category table controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/{categoryId}")
    public R getCategoryById(@PathVariable String categoryId) {
        return R.ok().setData(categoryService.getById(Integer.parseInt(categoryId)));
    }

    @GetMapping("/all")
    public R getCategoriesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Category> thePage = categoryService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }
}
