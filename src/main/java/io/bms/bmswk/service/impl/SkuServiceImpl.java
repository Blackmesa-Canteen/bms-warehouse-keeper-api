package io.bms.bmswk.service.impl;

import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.mapper.SkuMapper;
import io.bms.bmswk.service.ISkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * item table service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

}
