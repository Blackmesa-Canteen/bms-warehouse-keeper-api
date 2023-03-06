package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.mapper.SkuMapper;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.entity.Spu;
import io.bms.bmswk.mapper.SpuMapper;
import io.bms.bmswk.service.ISpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * product table service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {
    @Autowired
    SpuMapper spuMapper;

    @Autowired
    SkuMapper skuMapper;

    @Override
    @Transactional
    public void deleteSpuById(Integer spuId) {
        QueryWrapper<Sku> skuQueryWrapper = new QueryWrapper<>();
        skuQueryWrapper.eq("spu_id", spuId);
        if (skuMapper.exists(skuQueryWrapper)) {
            throw new RequestException("spu is already used in sku, reject deletion");
        }

        spuMapper.deleteById(spuId);
    }
}
