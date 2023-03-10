package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * item category table service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface ICategoryService extends IService<Category> {

    /**
     * try to delete category and its category params
     *
     * If category is used in spus, reject
     * @param categoryId int category id
     */
    void deleteCategoryById(Integer categoryId);

}
