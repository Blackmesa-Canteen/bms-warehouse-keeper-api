package io.bms.bmswk.model.vo;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-02 16:48
 */
public class WarehouseVO {

    private Integer warehouseId;
    private String warehouseName;
    private String address;

    private Integer cityId;

    private String cityName;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
