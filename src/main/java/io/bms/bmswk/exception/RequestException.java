package io.bms.bmswk.exception;

/**
 * <p>
 * request error
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:41
 */
public class RequestException extends BaseException{
    public RequestException() {
        super(ExceptionCodeEnum.REQUEST_GENERIC_EXCEPTION.getMessage(), ExceptionCodeEnum.REQUEST_GENERIC_EXCEPTION.getCode());
    }

    public RequestException(String msg) {
        super(msg, ExceptionCodeEnum.REQUEST_GENERIC_EXCEPTION.getCode());
    }
}
