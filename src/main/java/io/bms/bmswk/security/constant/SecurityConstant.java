package io.bms.bmswk.security.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * <p>
 * constants for security modules
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:50
 */
public class SecurityConstant {

    /**
     * Token header name
     */
    public final static String AUTH_HEADER_NAME = "Authorization";
    public final static String AUTH_TOKEN_PREFIX = "Bearer ";

    /**
     * auth filter name
     */
    public final static String AUTH_FILTER_NAME = "TokenAuthFilter";

    /** jwt token related constant */
    public final static String JWT_KEY = System.getenv("JWT_KEY");
    public final static String JWT_ISSUER = System.getenv("JWT_ISSUER");
    public final static long JWT_VALIDITY_MS =
            System.getenv("JWT_VALIDITY_MS") == null ? 24 * 60 * 60 * 1000
                    : Long.parseLong(System.getenv("JWT_VALIDITY_MS"));

    /** JWT cliam names */
    public final static String JWT_CLAIM_USER_PRIMARY_KEY = "userPk";
    public final static String JWT_CLAIM_LOGIN_ID = "loginId";
    public final static String JWT_CLAIM_USERNAME = "userName";
    public final static String JWT_CLAIM_ROLE_ID = "roleId";


    /** role names for RBAC */
    public final static String ADMIN_ROLE_NAME = "admin";
    public final static String CONSUMER_ROLE_NAME = "consumer";
    public final static String PURCHASER_ROLE_NAME = "purchaser";
    public final static String WAREHOUSE_KEEPER_ROLE_NAME = "warehouse_keeper";
    public final static String SUPER_ROLE_NAME = "super";

    /** permission names for RBAC */
    public final static String INVENTORY_PURCHASE_PERMISSION = "inventory_purchase";
    public final static String INVENTORY_CONSUME_PERMISSION = "inventory_consume";
    public final static String INVENTORY_MANAGE_PERMISSION = "inventory_manage";
    public final static String USER_MANAGE_PERMISSION = "user_manage";
    public final static String INVENTORY_SEE = "inventory_see";

    /**
     * shiro
     */
    public final static String REALM_NAME_BMS_WK = "BmsWkRealm";
}
