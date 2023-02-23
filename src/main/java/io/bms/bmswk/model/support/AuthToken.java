package io.bms.bmswk.model.support;

import java.util.Date;

/**
 * @author 996Worker
 * @description token for JWT authentication
 * @since 2022-08-03 23:35
 */
public class AuthToken {

    private String token;

    private Integer roleId;

    private String roleName;

    private Integer userId;

    private String userName;

    private String loginId;

    private Date issuedDate;

    private Date expirationDate;

    public AuthToken() {
    }


}
