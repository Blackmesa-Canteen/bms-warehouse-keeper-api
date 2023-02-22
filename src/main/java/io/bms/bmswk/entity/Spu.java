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
 * product table
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@TableName("t_spu")
@ApiModel(value = "Spu object", description = "product table")
public class Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("primary_key")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("product title")
    private String name;

    @ApiModelProperty("category ID")
    private Integer categoryId;

    @ApiModelProperty("is on sale for consumer to pick up")
    private Boolean saleable;

    @ApiModelProperty("is valid")
    private Boolean valid;

    private LocalDateTime dtCreated;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getSaleable() {
        return saleable;
    }

    public void setSaleable(Boolean saleable) {
        this.saleable = saleable;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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
        return "Spu{" +
            "id = " + id +
            ", name = " + name +
            ", categoryId = " + categoryId +
            ", saleable = " + saleable +
            ", valid = " + valid +
            ", dtCreated = " + dtCreated +
            ", dtUpdated = " + dtUpdated +
        "}";
    }
}
