package io.bms.bmswk.security.service.impl;

import io.bms.bmswk.exception.AuthException;
import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.security.service.IAuthService;
import io.bms.bmswk.security.util.ITokenHelper;
import io.bms.bmswk.service.IUserService;
import io.bms.bmswk.security.util.EncryptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * authentication services
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-28 11:34
 */
@Service
public class AuthService implements IAuthService {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenHelper tokenHelper;

    @Override
    public String loginUser(String loginId, String password) throws AuthException {
        User userRecord = userService.getUserByLoginId(loginId);
        // if user not exist
        if (userRecord == null) {
            LOGGER.info(String.format("User login Id: %s doesn't exist.", loginId));
            throw new AuthException(
                    ExceptionCodeEnum.AUTH_EXCEPTION.getMessage()
            );
        }

        String cypher = userRecord.getPassword();
        if (!EncryptionUtils.checkpw(password, cypher)) {
            // if password not correct
            LOGGER.info(String.format("User login Id: %s password not correct.", loginId));
            throw new AuthException(
                    ExceptionCodeEnum.AUTH_EXCEPTION.getMessage()
            );
        }

        // gen token
        String authToken = tokenHelper.genTokenStr(
                userRecord.getId(),
                userRecord.getLoginId(),
                userRecord.getName(),
                userRecord.getRoleId()
        );

        return authToken;
    }

    @Override
    public void registerUser(String loginId, String name, String password, Integer roleId, String phone) {
        userService.registerUser(
                loginId,
                name,
                password,
                roleId,
                phone
        );
    }

    @Override
    public String refreshTokenStr(String tokenStr) {
        return tokenHelper.refreshTokenStr(tokenStr);
    }
}
