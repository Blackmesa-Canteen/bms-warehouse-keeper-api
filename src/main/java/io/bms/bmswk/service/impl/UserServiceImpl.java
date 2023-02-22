package io.bms.bmswk.service.impl;

import io.bms.bmswk.entity.User;
import io.bms.bmswk.mapper.UserMapper;
import io.bms.bmswk.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
