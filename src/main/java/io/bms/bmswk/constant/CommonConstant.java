package io.bms.bmswk.constant;

/**
 * <p>
 * constants
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-23 10:32
 */
public class CommonConstant {

    /** Status enums */
    /** user status working */
    public final static Byte STAFF_WORKING = 1;

    /** user status on vacation */
    public final static Byte STAFF_ON_VACATION = 2;

    /** user status quit */
    public final static Byte STAFF_RESIGNED = 3;

    /** user status disabled */
    public final static Byte STAFF_BANNED = 4;

    /** purchase/consume request pending for audit */
    public final static Byte OPERATION_PENDING = 1;

    /** purchase/consume request pending passed an audit */
    public final static Byte OPERATION_FINISHED = 2;

    /** purchase/consume request is rejected by an audit */
    public final static Byte OPERATION_REJECTED = 3;
}
