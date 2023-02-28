package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * post json body for create a new warehouse
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-28 12:09
 */
public class WarehouseCreateParam {

    @NotNull(message = "name should exist")
    private String name;

    @NotNull(message = "address should exist")
    private String address;

    @NotNull(message = "cityId should exist")
    private Integer cityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
