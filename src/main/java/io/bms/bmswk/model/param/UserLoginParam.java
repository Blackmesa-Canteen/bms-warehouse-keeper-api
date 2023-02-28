package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 14:55
 */
public class UserLoginParam {

    @NotNull(message = "login id should not be null")
    private String loginId;

    @NotNull(message = "login password should not be null")
    private String password;

    public UserLoginParam(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
