package io.bms.bmswk.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:43
 */
public class DuplicatedUserException extends BaseException{

    public DuplicatedUserException() {
        super(ExceptionCodeEnum.USER_EXIST_EXCEPTION.getMessage(),
                ExceptionCodeEnum.USER_EXIST_EXCEPTION.getCode());
    }
}
