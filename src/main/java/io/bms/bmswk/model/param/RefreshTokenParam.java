package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-04 22:28
 */
public class RefreshTokenParam {

    @NotNull(message = "token should not be null")
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
