package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.bms.bmswk.exception.NoStockException;
import io.bms.bmswk.model.entity.WarehouseSku;
import io.bms.bmswk.mapper.WarehouseSkuMapper;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IWarehouseSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bms.bmswk.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class WarehouseSkuServiceImpl extends ServiceImpl<WarehouseSkuMapper, WarehouseSku> implements IWarehouseSkuService {


    @Override
    public void addSkuInWarehouse(Integer warehouseId, Integer skuId, Integer num, String unit) {
        QueryWrapper<WarehouseSku> wrapper = new QueryWrapper<>();
        wrapper.eq("warehouse_id", warehouseId)
                .eq("sku_id", skuId);

        synchronized (this) {
            WarehouseSku warehouseSku = this.getOne(wrapper);
            if (warehouseSku == null) {
                // if new in the stock
                warehouseSku = new WarehouseSku();
                warehouseSku.setWarehouseId(warehouseId);
                warehouseSku.setSkuId(skuId);
                warehouseSku.setNum(num);
                if (StringUtils.isEmpty(unit)) {
                    warehouseSku.setUnit("unit");
                } else {
                    warehouseSku.setUnit(unit);
                }

                this.save(warehouseSku);
            } else {
                // if already in stock
                Integer originalNum = warehouseSku.getNum();
                Integer targetNum = originalNum + num;
                warehouseSku.setNum(targetNum);
                this.updateById(warehouseSku);
            }
        }
    }

    @Override
    public void deductSkuInWareHouse(Integer warehouseId, Integer skuId, Integer num) {
        QueryWrapper<WarehouseSku> wrapper = new QueryWrapper<>();
        wrapper.eq("warehouse_id", warehouseId)
                .eq("sku_id", skuId);

        synchronized (this) {
            WarehouseSku warehouseSku = this.getOne(wrapper);
            if (warehouseSku == null) {
                // if not exist the stock
                throw new NoStockException("Target product not exist in the designated warehouse");
            } else {
                // if already in stock
                Integer originalNum = warehouseSku.getNum();

                if (num > originalNum) {
                    throw new NoStockException("No enough product to be taken in the warehouse");
                }

                Integer targetNum = originalNum - num;
                warehouseSku.setNum(targetNum);
                this.updateById(warehouseSku);
            }
        }
    }
}
