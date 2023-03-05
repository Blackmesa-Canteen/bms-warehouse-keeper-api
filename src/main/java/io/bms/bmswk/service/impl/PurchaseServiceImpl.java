package io.bms.bmswk.service.impl;

import io.bms.bmswk.constant.CommonConstant;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.model.entity.Purchase;
import io.bms.bmswk.mapper.PurchaseMapper;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {

    @Autowired
    private ISkuService skuService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IWarehouseSkuService warehouseSkuService;

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public void createOnePurchaseOrder(Integer skuId, Integer warehouseId, Integer purchaserId, Integer num, BigDecimal price) {
        synchronized (this) {
            // check existence
            Sku sku = skuService.getById(skuId);
            if (sku == null) {
                throw new RequestException("sku id not exist");
            }
            Warehouse warehouse = warehouseService.getById(warehouseId);
            if (warehouse == null) {
                throw new RequestException("warehouse not exist");
            }
            User user = userService.getById(purchaserId);
            if (user == null) {
                throw new RequestException("purchaser user not exist");
            }

            Purchase purchase = new Purchase();
            purchase.setSkuId(skuId);
            purchase.setWarehouseId(warehouseId);
            purchase.setPurchaserId(purchaserId);
            purchase.setNum(num);
            purchase.setPrice(price);
            purchase.setStatus(CommonConstant.OPERATION_PENDING);

            this.save(purchase);
        }
    }

    @Override
    @Transactional
    public void updateOnePurchaseOrder(Integer purchaseId, Integer num, Byte status, Integer keeperId) {
        synchronized (this) {
            Purchase purchase = this.getById(purchaseId);
            if (purchase == null) {
                throw new RequestException("target purhchase is not exist");
            }

            if (keeperId != null) {
                User keeper = userService.getById(keeperId);
                if (keeper == null) {
                    throw new RequestException("keeper user not exist");
                }
            }

            purchase.setNum(num);
            purchase.setStatus(status);
            if (keeperId != null) {
                purchase.setKeeperId(keeperId);
            }

            this.updateById(purchase);
        }
    }

    @Override
    @Transactional
    public void auditPurchaseOrderStatus(Integer purchaseId, Integer keeperId, Boolean isConfirmed) {
        synchronized (this) {
            Purchase purchase = this.getById(purchaseId);
            if (purchase == null) {
                throw new RequestException("target purchase is not exist");
            }

            if (!purchase.getStatus().equals(CommonConstant.OPERATION_PENDING)) {
                throw new RequestException("the purchase request already been audited");
            }

            User keeper = userService.getById(keeperId);
            if (keeper == null) {
                throw new RequestException("keeper user not exist");
            }

            // update request status
            if (isConfirmed) {
                try {
                    warehouseSkuService.addSkuInWarehouse(
                            purchase.getWarehouseId(),
                            purchase.getSkuId(),
                            purchase.getNum(),
                            null
                    );
                } catch (Exception e) {
                    // if exception occured when add stock
                    purchase.setStatus(CommonConstant.OPERATION_REJECTED);
                    purchase.setKeeperId(keeperId);
                    this.updateById(purchase);
                    throw e;
                }

                purchase.setStatus(CommonConstant.OPERATION_FINISHED);
            } else {
                purchase.setStatus(CommonConstant.OPERATION_REJECTED);
            }

            purchase.setKeeperId(keeperId);
            this.updateById(purchase);
        }
    }
}
