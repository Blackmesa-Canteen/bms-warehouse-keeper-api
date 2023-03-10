package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.Spu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * product table service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface ISpuService extends IService<Spu> {

    /**
     * delete spu by id. If spu is used in sku table, reject.
     * @param spuId int
     */
    void deleteSpuById(Integer spuId);
}
