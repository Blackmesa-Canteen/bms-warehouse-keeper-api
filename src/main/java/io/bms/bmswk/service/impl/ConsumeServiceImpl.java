package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.bms.bmswk.constant.CommonConstant;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.model.entity.Consume;
import io.bms.bmswk.mapper.ConsumeMapper;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class ConsumeServiceImpl extends ServiceImpl<ConsumeMapper, Consume> implements IConsumeService {

    @Autowired
    private ConsumeMapper consumeMapper;

    @Autowired
    private ISkuService skuService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    IWarehouseSkuService warehouseSkuService;

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public void createOneConsumeOrder(Integer skuId, Integer warehouseId, Integer consumerId, Integer num) {

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
            User user = userService.getById(consumerId);
            if (user == null) {
                throw new RequestException("consumer user not exist");
            }

            Consume consume = new Consume();
            consume.setSkuId(skuId);
            consume.setWarehouseId(warehouseId);
            consume.setConsumerId(consumerId);
            consume.setNum(num);
            consume.setStatus(CommonConstant.OPERATION_PENDING);


            this.save(consume);
        }
    }

    @Override
    @Transactional
    public void updateOneConsumeOrder(Integer consumeId, Integer num, Byte status, Integer keeperId) {

        synchronized (this) {
            Consume consume = this.getById(consumeId);
            if (consume == null) {
                throw new RequestException("target consume is not exist");
            }

            if (keeperId != null) {
                User keeper = userService.getById(keeperId);
                if (keeper == null) {
                    throw new RequestException("keeper user not exist");
                }
            }

            consume.setNum(num);
            consume.setStatus(status);
            if (keeperId != null) {
                consume.setKeeperId(keeperId);
            }

            this.updateById(consume);
        }
    }

    @Override
    @Transactional
    public void auditConsumeOrderStatus(Integer consumeId, Integer keeperId, Boolean isConfirmed) {
        synchronized (this) {
            Consume consume = this.getById(consumeId);
            if (consume == null) {
                throw new RequestException("target consume is not exist");
            }

            if (!consume.getStatus().equals(CommonConstant.OPERATION_PENDING)) {
                throw new RequestException("the consume request already been audited");
            }

            User keeper = userService.getById(keeperId);
            if (keeper == null) {
                throw new RequestException("keeper user not exist");
            }

            // update order status
            if (isConfirmed) {

                try {
                    warehouseSkuService.deductSkuInWareHouse(
                            consume.getWarehouseId(),
                            consume.getSkuId(),
                            consume.getNum()
                    );
                } catch (Exception e) {
                    // if exception occured when deduct stock
                    consume.setStatus(CommonConstant.OPERATION_REJECTED);
                    consume.setKeeperId(keeperId);
                    this.updateById(consume);
                    throw e;
                }

                consume.setStatus(CommonConstant.OPERATION_FINISHED);
            } else {
                consume.setStatus(CommonConstant.OPERATION_REJECTED);
            }

            consume.setKeeperId(keeperId);
            this.updateById(consume);
        }
    }
}
