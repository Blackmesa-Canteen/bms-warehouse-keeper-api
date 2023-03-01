package io.bms.bmswk.model.param;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @since 2023-03-01 15:58
 */
public class CategoryCreateParam {

    @NotNull
    String name;

    @NotNull
    List<CategoryParamCreateParam> params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryParamCreateParam> getParams() {
        return params;
    }

    public void setParams(List<CategoryParamCreateParam> params) {
        this.params = params;
    }
}
