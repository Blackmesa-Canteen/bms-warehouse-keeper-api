package io.bms.bmswk.exception;

import org.apache.http.HttpStatus;

/**
 * business exception code
 * @author: 996worker
 * @since: 2020-05-27 17:29
 * exception code with info
 * 1. exception code is 5 digits
 * 2. first 2 digits is scenario，last 3 digits is specific exception code.
 * </br>
 * Scenario：
 *  10: Generic
 *  11: purchasing/in-stock related
 *  12: consuming/out-stock related
 *  13: warehouse management
 *  14：user management
 *
 *
 *
 **/

public enum ExceptionCodeEnum {

    /** ok status for R response */
    OK(HttpStatus.SC_OK, "ok"),

    /** 500 status for all internal server exception */
    INTERNAL_SERVER_EXCEPTION(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal server error"),

    /** default exception status code */
    UNKNOW_EXCEPTION(10000,"Unknown system exception"),

    /** universal exception status show to requests */
    REQUEST_GENERIC_EXCEPTION(10002, "Generic request exception"),

    /** request params failed to pass validation */
    VAILD_EXCEPTION(10400,"Param validation exceiption"),

    /** error occurs in purchasing */
    PRODUCT_IN_EXCEPTION(11000,"register product in stock error"),

    /** error in consume */
    PRODUCT_OUT_EXCEPTION(12000,"register product out stock error"),

    /** stock not enough */
    NO_STOCK_EXCEPTION(12001,"Insufficient stock."),

    /** product is sealed */
    PRDUCT_SEALED_EXCEPTION(12002,"Product is sealed and can not be out of warehouse."),

    /** error occured in warehouse management */
    WAREHOUSE_MANAGEMENT_EXCEPTION(13000,"warehouse management error"),

    PRODUCT_NOT_FOUND_EXCEPTION(13404,"warehouse product not found"),
    USER_EXIST_EXCEPTION(14001,"Duplicated user"),

    /** generic auth exception */
    AUTH_EXCEPTION(14402,"username or password error"),

    /** no permission exception */
    NO_PERMISSION_EXCEPTION(14403,"no permission"),
    ;

    private Integer code;

    private String message;

    ExceptionCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
