package io.bms.bmswk.model.dto;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-06 13:58
 */
public class CategoryParamDTO {

    @NotNull
    String name;

    @NotNull
    Boolean isNumeric;

    @NotNull
    String unit;

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
