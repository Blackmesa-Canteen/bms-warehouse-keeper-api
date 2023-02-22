package io.bms.bmswk.service.impl;

import io.bms.bmswk.entity.Permission;
import io.bms.bmswk.mapper.PermissionMapper;
import io.bms.bmswk.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
