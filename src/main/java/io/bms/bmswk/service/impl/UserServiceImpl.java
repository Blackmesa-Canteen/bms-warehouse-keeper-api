package io.bms.bmswk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.bms.bmswk.exception.DuplicatedUserException;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.mapper.UserMapper;
import io.bms.bmswk.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bms.bmswk.security.util.EncryptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void registerUser(String loginId, String name, String password, Integer roleId, String phone) {
        // CHECK EXISTENCE
        Map<String, Object> map = new HashMap<>();
        map.put("login_id", loginId);

        // prevent duplicated record in multi admin
        synchronized (this) {
            List<User> users = userMapper.selectByMap(map);
            if (users.size() > 0) {
                throw new DuplicatedUserException();
            }

            // encrypt pass
            String cypher = EncryptionUtils.hashpw(password, EncryptionUtils.gensalt());


            // add to db
            User user = new User();
            user.setLoginId(loginId);
            user.setName(name);
            user.setPassword(cypher);
            user.setRoleId(roleId);
            user.setPhone(phone);
            userMapper.insert(user);
        }
    }

    @Override
    public User getUserByLoginId(String loginId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_id", loginId);
        return userMapper.selectOne(wrapper);
    }
}
