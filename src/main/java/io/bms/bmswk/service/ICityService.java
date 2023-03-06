package io.bms.bmswk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.bms.bmswk.model.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;
import io.bms.bmswk.model.support.Pagination;

import java.util.Map;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface ICityService extends IService<City> {

    /**
     * delete target city by id
     * if city is used in warehouse, reject
     * @param cityId
     */
    void deleteCityById(Integer cityId);

}
