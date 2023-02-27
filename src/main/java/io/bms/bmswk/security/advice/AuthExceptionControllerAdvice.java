package io.bms.bmswk.security.advice;

import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.model.support.R;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *  related auth exception handler
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-27 16:01
 */
@RestControllerAdvice
public class AuthExceptionControllerAdvice {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * handle shiro auth exception
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public R handleShiroException(ShiroException ex) {
        LOGGER.error("shiro exception: +" + ex.getMessage(), ex);
        return R.error(
                ExceptionCodeEnum.AUTH_EXCEPTION.getCode(),
                ExceptionCodeEnum.AUTH_EXCEPTION.getMessage()
        );
    }

    /**
     * handle shiro unauthorized exception
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public R handleForbiddenException() {
        return R.error(
                ExceptionCodeEnum.NO_PERMISSION_EXCEPTION.getCode(),
                ExceptionCodeEnum.NO_PERMISSION_EXCEPTION.getMessage()
        );
    }
}
