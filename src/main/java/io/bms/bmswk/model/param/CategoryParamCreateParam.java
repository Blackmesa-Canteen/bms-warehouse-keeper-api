package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-01 16:05
 */
public class CategoryParamCreateParam {

    @NotNull
    Integer categoryId;

    @NotNull
    String name;

    @NotNull
    Boolean isNumeric;

    @NotNull
    String unit;

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
}
