package io.bms.bmswk.model.param;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 14:54
 */
public class UserRegisterParam {

    private String loginId;

    private String name;

    private String password;

    private Integer roleId;

    private String phone;

    public UserRegisterParam(String loginId, String name, String password, Integer roleId, String phone) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.phone = phone;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
