package io.bms.bmswk.constant;

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



    public final static String JWT_KEY = System.getenv("JWT_KEY");
    public final static String JWT_ISSUER = System.getenv("JWT_ISSUER");
    public final static long JWT_VALIDITY_MS = 24 * 60 * 60 * 1000;


}
