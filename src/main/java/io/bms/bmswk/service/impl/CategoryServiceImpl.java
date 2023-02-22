package io.bms.bmswk.service.impl;

import io.bms.bmswk.model.entity.Category;
import io.bms.bmswk.mapper.CategoryMapper;
import io.bms.bmswk.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
