package io.bms.bmswk.service.impl;

import io.bms.bmswk.exception.DuplicatedUserException;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.mapper.UserMapper;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.bms.bmswk.util.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private UserMapper userMapper;

    @Override
    public R registerUser(String loginId, String name, String password, Integer roleId, String phone) {
        // CHECK EXISTENCE
        Map<String, Object> map = new HashMap<>();
        map.put("login_id", loginId);
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

        return R.ok();
    }
}
