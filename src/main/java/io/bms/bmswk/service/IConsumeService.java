package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.Consume;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface IConsumeService extends IService<Consume> {

    /**
     * create one consume order
     * @param skuId Integer
     * @param warehouseId Integer
     * @param consumerId Integer, consumer user id (pk)
     */
    void createOneConsumeOrder(Integer skuId, Integer warehouseId, Integer consumerId, Integer num);

    /**
     *
     * @param consumeId consume order id
     * @param num num of items
     * @param status status byte, see constant
     * @param keeperId warehouse keeper user id. Can be null if don't want to update it
     */
    void updateOneConsumeOrder(Integer consumeId, Integer num, Byte status, Integer keeperId);

    /**
     * used for warehouse keeper to audit consume order
     * @param consumeId consume order id
     * @param keeperId warehouse keeper user id
     * @param isConfirmed if confirm, order finish, and put the stuff in warehouse
     */
    void auditConsumeOrderStatus(Integer consumeId, Integer keeperId, Boolean isConfirmed);

}
