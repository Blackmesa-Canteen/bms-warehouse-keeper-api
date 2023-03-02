package io.bms.bmswk.mapper;

import io.bms.bmswk.model.dto.InventoryItemDTO;
import io.bms.bmswk.model.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper interface
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    /**
     * get inventory sku item details
     * @param warehouseId int
     * @param offset int
     * @param size int
     * @return list of inventoryItemDTO
     */
    List<InventoryItemDTO> listWarehouseInventoryByIdByPage(
            @Param("warehouseId") Integer warehouseId,
            @Param("offset") Integer offset,
            @Param("size") Integer size
    );
}
