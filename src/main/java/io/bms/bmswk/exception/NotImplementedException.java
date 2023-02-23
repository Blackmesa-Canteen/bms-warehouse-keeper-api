package io.bms.bmswk.exception;

/**
 * @author 996Worker
 * @description
 * @create 2022-08-29 15:11
 */
public class NotImplementedException extends BaseException {
    public NotImplementedException() {
        super("API not implemented", ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode());
    }
}
