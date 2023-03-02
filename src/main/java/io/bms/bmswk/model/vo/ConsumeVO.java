package io.bms.bmswk.model.vo;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-02 17:04
 */
public class ConsumeVO {

    private Integer consumeId;

    private Integer skuId;

    private String skuName;

    private Integer num;

    private Integer warehouseId;

    private String warehouseName;

    private Integer consumerId;

    private String consumerName;

    private Byte status;

    private String statusName;

    private Integer keeperId;

    private String keeperName;

    public Integer getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Integer consumeId) {
        this.consumeId = consumeId;
    }

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

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
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

    @Override
    public String toString() {
        return "ConsumeVO{" +
                "consumeId=" + consumeId +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", num=" + num +
                ", warehouseId=" + warehouseId +
                ", warehouseName='" + warehouseName + '\'' +
                ", consumerId=" + consumerId +
                ", consumerName='" + consumerName + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", keeperId=" + keeperId +
                ", keeperName='" + keeperName + '\'' +
                '}';
    }
}
