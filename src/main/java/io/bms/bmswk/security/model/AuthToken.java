package io.bms.bmswk.security.model;

import java.util.Date;

/**
 * @author 996Worker
 * @description token for JWT authentication
 * @since 2022-08-03 23:35
 */
public class AuthToken {

    private String token;

    private Integer roleId;

    private Integer userPk;

    private String userName;

    private String loginId;

    private Date issuedDate;

    private Date expirationDate;

    public AuthToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserPk() {
        return userPk;
    }

    public void setUserPk(Integer userPk) {
        this.userPk = userPk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
