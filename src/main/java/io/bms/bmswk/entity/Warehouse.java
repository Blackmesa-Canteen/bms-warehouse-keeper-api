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
@TableName("t_warehouse")
@ApiModel(value = "Warehouse对象", description = "")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String address;

    private Integer cityId;

    private LocalDateTime dtCreated;

    private LocalDateTime dtUpdated;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
            "id = " + id +
            ", address = " + address +
            ", cityId = " + cityId +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
            ", name = " + name +
        "}";
    }
}
