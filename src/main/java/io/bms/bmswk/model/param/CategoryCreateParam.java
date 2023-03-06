package io.bms.bmswk.model.param;

import io.bms.bmswk.model.dto.CategoryParamDTO;

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
    List<CategoryParamDTO> params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryParamDTO> getParams() {
        return params;
    }

    public void setParams(List<CategoryParamDTO> params) {
        this.params = params;
    }
}
