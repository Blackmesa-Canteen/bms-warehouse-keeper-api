package io.bms.bmswk.security.service.impl;

import io.bms.bmswk.model.entity.Role;
import io.bms.bmswk.mapper.RoleMapper;
import io.bms.bmswk.security.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
