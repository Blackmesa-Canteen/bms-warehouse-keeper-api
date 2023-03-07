package io.bms.bmswk.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@TableName("t_purchase")
@ApiModel(value = "Purchase object", description = "Purchase/In-Stock request")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("Primary key id")
    private Integer id;

    @ApiModelProperty("InStock sku id")
    private Integer skuId;

    @ApiModelProperty("instock number")
    private Integer num;

    @ApiModelProperty("Target warehouse")
    private Integer warehouseId;

    @ApiModelProperty("user id who want to in stock the sku")
    private Integer purchaserId;

    @ApiModelProperty("1 pending for audit; 2 finished; 3 rejected")
    private Byte status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime dtCreated;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime dtUpdated;

    @ApiModelProperty("Price to purchase the item for the warehouse")
    private BigDecimal price;

    @ApiModelProperty("audit warehouse keeper id")
    private Integer keeperId;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public LocalDateTime getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(LocalDateTime dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(Integer keeperId) {
        this.keeperId = keeperId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Purchase{" +
            "id = " + id +
            ", skuId = " + skuId +
            ", num = " + num +
            ", warehouseId = " + warehouseId +
            ", purchaserId = " + purchaserId +
            ", status = " + status +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
            ", price = " + price +
            ", keeperId = " + keeperId +
        "}";
    }
}
