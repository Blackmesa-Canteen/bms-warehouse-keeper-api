package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.WarehouseSku;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface IWarehouseSkuService extends IService<WarehouseSku> {

    /**
     * confirm add a product into ware house
     * @param warehouseId target warehouse id
     * @param skuId target sku id
     * @param num add num
     * @param unit string
     */
    void addSkuInWarehouse(Integer warehouseId, Integer skuId, Integer num, String unit);

    /**
     * confrim deduct num of product from warehouse
     * @param warehouseId int
     * @param skuId int
     * @param num int
     */
    void deductSkuInWareHouse(Integer warehouseId, Integer skuId, Integer num);
}
