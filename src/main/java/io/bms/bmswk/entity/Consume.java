package io.bms.bmswk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(value = "Consume对象", description = "")
public class Consume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer skuId;

    private Integer num;

    private Integer warehouseId;

    private Integer consumerId;

    @ApiModelProperty("1 pending for audit; 2 finished")
    private Byte status;

    private LocalDateTime dtCreated;

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
