package io.bms.bmswk.service.impl;

import io.bms.bmswk.exception.NotImplementedException;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.mapper.CityMapper;
import io.bms.bmswk.model.dto.InventoryItemDTO;
import io.bms.bmswk.model.entity.City;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.mapper.WarehouseMapper;
import io.bms.bmswk.model.vo.InventoryItemVO;
import io.bms.bmswk.service.ICityService;
import io.bms.bmswk.service.IWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

    @Autowired
    CityMapper cityMapper;

    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    @Transactional
    public void createWareHouse(String name, String address, Integer cityId) {

        synchronized (this) {
            // check wether city is exist or not
            City city = cityMapper.selectById(cityId);
            if (city == null) {
                throw new RequestException("City id not exist in db");
            }

            Warehouse warehouse = new Warehouse();
            warehouse.setName(name);
            warehouse.setAddress(address);
            warehouse.setCityId(cityId);

            warehouseMapper.insert(warehouse);
        }
    }

    @Override
    public List<InventoryItemDTO> listWarehouseInventoryByIdByPage(Integer page, Integer size, Integer warehouseId) {

        int offset = (page - 1) * size;
        if (offset < 0) {
            offset = 0;
        }

        List<InventoryItemDTO> res = warehouseMapper.listWarehouseInventoryByIdByPage(
                warehouseId,
                offset,
                size
        );

        return res;
    }
}
