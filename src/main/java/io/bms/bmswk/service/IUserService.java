package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import io.bms.bmswk.model.support.R;

/**
 * <p>
 *  service
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface IUserService extends IService<User> {

    /**
     * Create a user account
     * @param loginId email for login
     * @param name user display name
     * @param password plain password
     * @param roleId user role id
     * @param phone user phone
     * @return ok if done, else exception
     */
    void registerUser(String loginId, String name, String password,
                          Integer roleId, String phone);

    /**
     * get user info by user id
     * @param loginId
     * @return user info
     */
    User getUserByLoginId(String loginId);
}
