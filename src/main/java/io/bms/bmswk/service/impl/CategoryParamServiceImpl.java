package io.bms.bmswk.service.impl;

import io.bms.bmswk.model.entity.CategoryParam;
import io.bms.bmswk.mapper.CategoryParamMapper;
import io.bms.bmswk.service.ICategoryParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
