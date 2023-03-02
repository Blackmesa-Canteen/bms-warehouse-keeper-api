package io.bms.bmswk.security.util;

import io.bms.bmswk.exception.AuthException;
import io.bms.bmswk.security.model.AuthToken;

public interface ITokenHelper {

    /**
     * check if valid token or not.
     *
     * @param token token string
     * @return true if valid
     */
    @Deprecated
    boolean validateTokenStr(String token) throws AuthException;

    /**
     * get token info dto from token, at the same time validate the token
     *
     * @return token str
     */
    AuthToken validateAndDecodeTokenStr(String token) throws AuthException;

    /**
     * decode token without validation
     * @param token string
     * @return auth obj
     * @throws AuthException exception
     */
    AuthToken decodeTokenStr(String token) throws AuthException;

    /**
     * gen token with expiration date
     * @param userPk user pk int
     * @param loginId login id string
     * @param userName user display name string
     * @param roleId role id int
     * @return auth token
     * @throws AuthException
     */
    String genTokenStr(Integer userPk, String loginId, String userName, Integer roleId) throws AuthException;
}
