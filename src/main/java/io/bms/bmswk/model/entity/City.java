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
@TableName("t_city")
@ApiModel(value = "City object", description = "")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("Primary key id")
    private Integer id;

    @ApiModelProperty("city display name")
    private String name;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime dtCreated;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime dtUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "City{" +
            "id = " + id +
            ", name = " + name +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
        "}";
    }
}
