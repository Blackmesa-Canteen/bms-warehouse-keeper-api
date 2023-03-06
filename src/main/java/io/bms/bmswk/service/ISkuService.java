package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * item table service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface ISkuService extends IService<Sku> {

    /**
     * get skus under spu
     * @param page
     * @param size
     * @param spuId
     * @return
     */
    List<Sku> getSkusBySpuId(Integer page, Integer size, Integer spuId);
}
