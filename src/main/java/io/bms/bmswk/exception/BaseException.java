package io.bms.bmswk.exception;

/**
 * <p>
 *  base system exception
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 11:29
 */
public class BaseException extends RuntimeException {

    protected int code = ExceptionCodeEnum.UNKNOW_EXCEPTION.getCode();
    protected String msg = ExceptionCodeEnum.UNKNOW_EXCEPTION.getMessage();

    public BaseException() {
        super(ExceptionCodeEnum.UNKNOW_EXCEPTION.getMessage());
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BaseException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BaseException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
