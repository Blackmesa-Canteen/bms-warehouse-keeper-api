package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.exception.RequestException;
import io.bms.bmswk.mapper.WarehouseMapper;
import io.bms.bmswk.model.entity.City;
import io.bms.bmswk.mapper.CityMapper;
import io.bms.bmswk.model.entity.Warehouse;
import io.bms.bmswk.model.support.Pagination;
import io.bms.bmswk.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bms.bmswk.service.IWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {
    private static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    @Transactional
    public void deleteCityById(Integer cityId) {
        QueryWrapper<Warehouse> warehouseQueryWrapper = new QueryWrapper<>();
        warehouseQueryWrapper.eq("city_id", cityId);
        if (warehouseMapper.exists(warehouseQueryWrapper)) {
            throw new RequestException(ExceptionCodeEnum.WAREHOUSE_MANAGEMENT_EXCEPTION.getCode(),
                    "city is already in used, can not remove");
        }

        this.removeById(cityId);
    }
}
