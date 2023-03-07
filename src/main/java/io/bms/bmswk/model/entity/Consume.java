package io.bms.bmswk.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
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
@TableName("t_consume")
@ApiModel(value = "Consume object", description = "Consume/Out-Stock requests")
public class Consume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("Primary key id")
    private Integer id;

    @ApiModelProperty("target sku id")
    private Integer skuId;

    @ApiModelProperty("number of out-stock sku")
    private Integer num;

    @ApiModelProperty("source warehouse id")
    private Integer warehouseId;

    @ApiModelProperty("user id who created this request")
    private Integer consumerId;

    @ApiModelProperty("1 pending for audit; 2 finished; 3 rejected")
    private Byte status;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean valid;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime dtCreated;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime dtUpdated;

    @ApiModelProperty("audit warehouse keeper id")
    private Integer keeperId;

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

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
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
        return "Consume{" +
            "id = " + id +
            ", skuId = " + skuId +
            ", num = " + num +
            ", warehouseId = " + warehouseId +
            ", consumerId = " + consumerId +
            ", status = " + status +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
            ", keeperId = " + keeperId +
        "}";
    }
}
