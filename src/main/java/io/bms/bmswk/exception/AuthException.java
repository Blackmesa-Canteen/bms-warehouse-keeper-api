package io.bms.bmswk.exception;

/**
 * <p>
 * authentication pwd error
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:41
 */
public class AuthException extends BaseException{
    public AuthException() {
        super(ExceptionCodeEnum.AUTH_EXCEPTION.getMessage(), ExceptionCodeEnum.AUTH_EXCEPTION.getCode());
    }
}
