package io.bms.bmswk.security.util;

import io.bms.bmswk.security.constant.SecurityConstant;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-27 16:25
 */
public class TokenStrUtils {

    public static String removeTokenPrefix(String token) {
        // if no bearer prefix, just return original
        if (!token.contains(SecurityConstant.AUTH_TOKEN_PREFIX)) {
            return token;
        }

        int prefixLength = SecurityConstant.AUTH_TOKEN_PREFIX.length();
        return token.substring(prefixLength);
    }
}
