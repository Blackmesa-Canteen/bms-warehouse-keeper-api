package io.bms.bmswk.model.entity;

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
@TableName("t_warehouse_sku")
@ApiModel(value = "WarehouseSku object", description = "")
public class WarehouseSku implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer warehouseId;

    private Integer skuId;

    private Integer num;

    private String unit;

    private LocalDateTime dtCreated;

    private LocalDateTime dtUpdated;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    @Override
    public String toString() {
        return "WarehouseSku{" +
            "warehouseId = " + warehouseId +
            ", skuId = " + skuId +
            ", num = " + num +
            ", unit = " + unit +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
        "}";
    }
}