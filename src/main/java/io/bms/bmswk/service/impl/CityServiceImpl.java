package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.bms.bmswk.model.entity.City;
import io.bms.bmswk.mapper.CityMapper;
import io.bms.bmswk.model.support.Pagination;
import io.bms.bmswk.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
