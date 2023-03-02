package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-02 10:56
 */
public class PurchaseCreateParam {

    @NotNull
    private Integer skuId;

    @NotNull
    private Integer num;

    @NotNull
    private Integer warehouseId;

    @NotNull
    private Integer purchaserId;

    @NotNull
    private Long price;
}
