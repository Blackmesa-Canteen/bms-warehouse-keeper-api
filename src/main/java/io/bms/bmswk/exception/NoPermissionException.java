package io.bms.bmswk.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:44
 */
public class NoPermissionException extends BaseException{

    public NoPermissionException() {
        super(ExceptionCodeEnum.NO_PERMISSION_EXCEPTION.getMessage(), ExceptionCodeEnum.NO_PERMISSION_EXCEPTION.getCode());
    }
}
