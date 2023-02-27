package io.bms.bmswk.exception;

import org.apache.http.HttpStatus;

/**
 * customized Exception
 *
 * @author xiaotian
 */
public class InternalException extends BaseException {
	public InternalException(Throwable e) {
		super(ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getMessage(),
				ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode(),
				e);
	}

	public InternalException(String msg) {
		super(msg,
				ExceptionCodeEnum.INTERNAL_SERVER_EXCEPTION.getCode());
	}

	public InternalException(Integer code, String msg) {
		super(msg, code);
	}
}
