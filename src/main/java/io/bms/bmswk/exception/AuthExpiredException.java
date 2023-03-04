package io.bms.bmswk.exception;

/**
 * <p>
 * token expired error
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:41
 */
public class AuthExpiredException extends AuthException{
    public AuthExpiredException() {
    }

    public AuthExpiredException(String msg) {
        super(msg);
    }
}
