package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.bms.bmswk.constant.CommonConstant;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.mapper.SkuMapper;
import io.bms.bmswk.mapper.UserMapper;
import io.bms.bmswk.mapper.WarehouseMapper;
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
    private SkuMapper skuMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    WarehouseMapper warehouseSkuMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IWarehouseSkuService warehouseSkuService;

    @Override
    @Transactional
    public void createOneConsumeOrder(Integer skuId, Integer warehouseId, Integer consumerId, Integer num) {
        synchronized (this) {
            // check existence
            Sku sku = skuMapper.selectById(skuId);
            if (sku == null) {
                throw new RequestException("sku id not exist");
            }
            Warehouse warehouse = warehouseMapper.selectById(warehouseId);
            if (warehouse == null) {
                throw new RequestException("warehouse not exist");
            }
            User user = userMapper.selectById(consumerId);
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
                User keeper = userMapper.selectById(keeperId);
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

            User keeper = userMapper.selectById(keeperId);
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
