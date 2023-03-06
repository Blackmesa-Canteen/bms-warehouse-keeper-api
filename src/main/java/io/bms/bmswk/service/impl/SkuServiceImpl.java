package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.mapper.SpuMapper;
import io.bms.bmswk.mapper.WarehouseSkuMapper;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.mapper.SkuMapper;
import io.bms.bmswk.model.entity.WarehouseSku;
import io.bms.bmswk.service.ISkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private WarehouseSkuMapper associateMapper;

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

    @Override
    @Transactional
    public void deleteSkuById(Integer skuId) {
        QueryWrapper<WarehouseSku> associateQuery = new QueryWrapper<>();
        associateQuery.eq("sku_id", skuId);
        if (associateMapper.exists(associateQuery)) {
            throw new RequestException("sku is in some warehouses now, can not directly remove");
        }

        this.removeById(skuId);
    }
}
