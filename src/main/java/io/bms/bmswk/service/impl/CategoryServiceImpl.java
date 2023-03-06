package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.mapper.CategoryParamMapper;
import io.bms.bmswk.mapper.SpuMapper;
import io.bms.bmswk.model.entity.Category;
import io.bms.bmswk.mapper.CategoryMapper;
import io.bms.bmswk.model.entity.CategoryParam;
import io.bms.bmswk.model.entity.Spu;
import io.bms.bmswk.service.ICategoryParamService;
import io.bms.bmswk.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bms.bmswk.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * item category table service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategoryParamMapper paramMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public void deleteCategoryById(Integer categoryId) {
        QueryWrapper<Spu> spuQueryWrapper = new QueryWrapper<>();
        spuQueryWrapper.eq("category_id", categoryId);
        if (spuMapper.exists(spuQueryWrapper)) {
            // check existence in spu
            throw new RequestException(ExceptionCodeEnum.WAREHOUSE_MANAGEMENT_EXCEPTION.getCode(),
                    "Can not delete category because it is already in use.");
        }

        // delete related param
        QueryWrapper<CategoryParam> paramQueryWrapper = new QueryWrapper<>();
        paramQueryWrapper.eq("category_id", categoryId);
        paramMapper.delete(paramQueryWrapper);

        // delete category
        categoryMapper.deleteById(categoryId);
    }
}
