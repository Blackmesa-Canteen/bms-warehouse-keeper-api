package io.bms.bmswk.model.dto;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-04 23:13
 */
public class UserLoginDTO {

    String token;

    Integer userId;

    String loginId;

    String userName;

    Integer roleId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
