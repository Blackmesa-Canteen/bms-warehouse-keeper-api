package io.bms.bmswk.security.service;

import io.bms.bmswk.exception.AuthException;
import io.bms.bmswk.model.dto.UserLoginDTO;

/**
 * @author xiaotian
 */
public interface IAuthService {

    /**
     * login user, then returns auth token string
     * @param loginId the unique loginId
     * @param password plain password
     * @return auth token if successful, otherwise, return null
     * @throws AuthException yes
     */
    UserLoginDTO loginUser(String loginId, String password) throws AuthException;

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
     * refresh outdated token string
     * @param tokenStr tokenStr
     * @return string new token
     */
    String refreshTokenStr (String tokenStr);
}
