package io.bms.bmswk.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:24
 */
public class NoStockException extends BaseException{
    public NoStockException() {
        super(ExceptionCodeEnum.NO_STOCK_EXCEPTION.getMessage(), ExceptionCodeEnum.NO_STOCK_EXCEPTION.getCode());
    }
}
