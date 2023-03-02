package io.bms.bmswk.model.vo;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-02 16:38
 */
public class InventoryItemVO {

    String warehouseName;

    Integer warehouseId;

    Integer categoryId;

    String skuName;

    Integer skuId;

    String spuName;

    Integer spuId;

    BigDecimal price;

    Boolean saleable;

    String param;

    Integer num;

    String unit;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getSaleable() {
        return saleable;
    }

    public void setSaleable(Boolean saleable) {
        this.saleable = saleable;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
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

    @Override
    public String toString() {
        return "InventoryItemDTO{" +
                "warehouseName='" + warehouseName + '\'' +
                ", warehouseId=" + warehouseId +
                ", categoryId=" + categoryId +
                ", skuName='" + skuName + '\'' +
                ", skuId=" + skuId +
                ", spuName='" + spuName + '\'' +
                ", spuId=" + spuId +
                ", price=" + price +
                ", saleable=" + saleable +
                ", param='" + param + '\'' +
                ", num=" + num +
                ", unit='" + unit + '\'' +
                '}';
    }
}
