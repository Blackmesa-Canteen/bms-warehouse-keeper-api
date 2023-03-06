package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.mapper.SpuMapper;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.mapper.SkuMapper;
import io.bms.bmswk.service.ISkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<Sku> getSkusBySpuId(Integer page, Integer size, Integer spuId) {
        QueryWrapper<Sku> skuQueryWrapper = new QueryWrapper<>();
        skuQueryWrapper.eq("spu_id", spuId);
        Page<Sku> skuPage = this.page(new Page<>(page, size), skuQueryWrapper);
        if (skuPage != null) {
            return skuPage.getRecords();
        } else {
            return new LinkedList<>();
        }
    }
}
