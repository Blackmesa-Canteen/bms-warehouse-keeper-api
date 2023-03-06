package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.bms.bmswk.model.entity.CategoryParam;
import io.bms.bmswk.mapper.CategoryParamMapper;
import io.bms.bmswk.service.ICategoryParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * parameter table for item categories service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class CategoryParamServiceImpl extends ServiceImpl<CategoryParamMapper, CategoryParam> implements ICategoryParamService {

    @Autowired
    CategoryParamMapper categoryParamMapper;

    @Override
    public List<CategoryParam> getAllParamsByCategoryId(Integer categoryId) {
        QueryWrapper<CategoryParam> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        return categoryParamMapper.selectList(wrapper);
    }
}
