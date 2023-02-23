package io.bms.bmswk.handler;

import io.bms.bmswk.exception.BaseException;
import io.bms.bmswk.exception.ExceptionCodeEnum;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.util.ExceptionUtils;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * <p>
 * exception handler
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 15:27
 */
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionControllerAdvice.class);

    /**
     * handles JSR303 validation exception
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R handleValidationException(ConstraintViolationException ex){
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }
        logger.error(msg.toString(),ex);

        return R.error(ExceptionCodeEnum.VAILD_EXCEPTION.getCode(),
                ExceptionCodeEnum.VAILD_EXCEPTION.getMessage()).setData(msg.toString());
    }

    /**
     * handles defined exception
     * @param ex the exception
     * @return Response
     */
    @ExceptionHandler(value = BaseException.class)
    public R handleException(BaseException ex) {
        logger.error(ex.getMsg(), ex);
        return R.error(ex.getCode(), ex.getMsg()).setData(ExceptionUtils.getStackTrace(ex));
    }

    /**
     * handles un-expected exception
     * @param ex the exception
     * @return Response
     */
    @ExceptionHandler(value = Exception.class)
    public R handleUnknown(Exception ex){
        logger.error(ex.getMessage(),ex);
        return R.error(ExceptionCodeEnum.UNKNOW_EXCEPTION.getCode(),
                ex.getMessage()).setData(ExceptionUtils.getStackTrace(ex));
    }

}
