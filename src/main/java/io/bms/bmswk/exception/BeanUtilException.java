package io.bms.bmswk.exception;

/**
 * <p>
 *  exception caused by Bean Util
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 15:03
 */
public class BeanUtilException extends BaseException{

    public BeanUtilException(String msg, Throwable e) {
        super(
                msg,
                ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode(),
                e
        );
    }
}
