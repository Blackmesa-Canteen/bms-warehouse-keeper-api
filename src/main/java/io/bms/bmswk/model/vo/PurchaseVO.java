package io.bms.bmswk.model.vo;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-02 17:04
 */
public class PurchaseVO {

    private Integer purchaseId;

    private Integer skuId;

    private String skuName;

    private Integer num;

    private Integer warehouseId;

    private String warehouseName;

    private Integer purchaserId;

    private String purchaserName;

    private Byte status;

    private String statusName;

    private Integer keeperId;

    private String keeperName;

    private BigDecimal price;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(Integer keeperId) {
        this.keeperId = keeperId;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PurchaseVO{" +
                "purchaseId=" + purchaseId +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", num=" + num +
                ", warehouseId=" + warehouseId +
                ", warehouseName='" + warehouseName + '\'' +
                ", purchaserId=" + purchaserId +
                ", purchaserName='" + purchaserName + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", keeperId=" + keeperId +
                ", keeperName='" + keeperName + '\'' +
                ", price=" + price +
                '}';
    }
}
