package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface IPurchaseService extends IService<Purchase> {

    /**
     * create one purchase order
     * @param skuId Integer
     * @param warehouseId Integer
     * @param purchaserId Integer, consumer user id (pk)
     * @param price BigDecimal price
     */
    void createOnePurchaseOrder(Integer skuId, Integer warehouseId, Integer purchaserId, Integer num, BigDecimal price);

    /**
     * ok
     * @param purchaseId order id
     * @param num num of items
     * @param status status byte, see constant
     * @param keeperId warehouse keeper user id. Can be null if don't want to update it
     */
    void updateOnePurchaseOrder(Integer purchaseId, Integer num, Byte status, Integer keeperId);

    /**
     * confim the purchase request
     * @param purchaseId purchase order/request id
     * @param keeperId warehouse keeper user id
     * @param isConfirmed whether approved or not
     */
    void auditPurchaseOrderStatus(Integer purchaseId, Integer keeperId, Boolean isConfirmed);

}
