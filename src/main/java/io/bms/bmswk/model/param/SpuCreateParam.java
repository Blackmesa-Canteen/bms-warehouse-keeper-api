package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-01 16:22
 */
public class SpuCreateParam {

    @NotNull(message = "name should not null")
    String name;

    @NotNull(message = "category id should not null")
    Integer categoryId;

    @NotNull(message = "saleable should not null")
    Boolean saleable;

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
}
