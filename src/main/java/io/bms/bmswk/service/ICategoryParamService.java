package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.CategoryParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * parameter table for item categories service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface ICategoryParamService extends IService<CategoryParam> {

    /**
     * get categoryAllParam by categoryId
     * @param categoryId integer category Id
     * @return List<CategoryParam>
     */
    List<CategoryParam> getAllParamsByCategoryId(Integer categoryId);

}
