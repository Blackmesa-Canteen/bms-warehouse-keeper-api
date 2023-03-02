package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    private BigDecimal price;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
