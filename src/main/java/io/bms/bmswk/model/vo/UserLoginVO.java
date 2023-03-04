package io.bms.bmswk.model.vo;

import io.bms.bmswk.model.dto.PermissionDTO;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-04 23:13
 */
public class UserLoginVO {

    String token;

    Integer userId;

    String loginId;

    String userName;

    Integer roleId;

    String roleName;

    List<PermissionDTO> permissions;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
