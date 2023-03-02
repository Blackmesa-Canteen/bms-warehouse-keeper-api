package io.bms.bmswk.service;

import io.bms.bmswk.model.dto.InventoryItemDTO;
import io.bms.bmswk.model.entity.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import io.bms.bmswk.model.vo.InventoryItemVO;

import java.util.List;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface IWarehouseService extends IService<Warehouse> {

    /**
     * create a new warehouse
     * @param name name of the warehouse
     * @param address address str
     * @param cityId city id int
     */
    void createWareHouse(String name, String address, Integer cityId);

    /**
     * list all inventory from warehouse
     * @param page int page
     * @param size int page size
     * @param warehouseId int target warehouse id
     * @return List of VOs
     */
    List<InventoryItemDTO> listWarehouseInventoryByIdByPage(
            Integer page,
            Integer size,
            Integer warehouseId
    );
}
