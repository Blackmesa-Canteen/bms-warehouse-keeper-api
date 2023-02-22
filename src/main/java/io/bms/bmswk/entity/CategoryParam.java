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
 * parameter table for item categories
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@TableName("t_category_param")
@ApiModel(value = "CategoryParam对象", description = "parameter table for item categories")
public class CategoryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer categoryId;

    private String name;

    @ApiModelProperty("is numeric parameter")
    private Boolean numeric;

    @ApiModelProperty("unit")
    private String unit;

    private LocalDateTime dtCreated;

    private LocalDateTime dtUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNumeric() {
        return numeric;
    }

    public void setNumeric(Boolean numeric) {
        this.numeric = numeric;
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
        return "CategoryParam{" +
            "id = " + id +
            ", categoryId = " + categoryId +
            ", name = " + name +
            ", numeric = " + numeric +
            ", unit = " + unit +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
        "}";
    }
}
