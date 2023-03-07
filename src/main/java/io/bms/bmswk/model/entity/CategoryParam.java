package io.bms.bmswk.model.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@ApiModel(value = "CategoryParam object", description = "parameters/attributes for product categories")
public class CategoryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("Primary key id")
    private Integer id;

    @ApiModelProperty("Attached category key id")
    private Integer categoryId;

    @ApiModelProperty("category param display name")
    private String name;

    @ApiModelProperty("is numeric parameter")
    private Boolean isNumeric;

    @ApiModelProperty("unit")
    private String unit;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime dtCreated;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime dtUpdated;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean valid;

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

    public Boolean getIsNumeric() {
        return isNumeric;
    }

    public void setIsNumeric(Boolean isNumeric) {
        this.isNumeric = isNumeric;
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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "CategoryParam{" +
            "id = " + id +
            ", categoryId = " + categoryId +
            ", name = " + name +
            ", numeric = " + isNumeric +
            ", unit = " + unit +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
        "}";
    }
}
