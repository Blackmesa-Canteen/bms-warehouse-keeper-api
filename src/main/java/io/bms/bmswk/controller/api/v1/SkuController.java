package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * item table controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/api/v1/sku")
public class SkuController {

    @Autowired
    private ISkuService skuService;

    @GetMapping("/{skuId}")
    public R getSkuById(@PathVariable String skuId) {
        Sku sku = skuService.getById(Integer.parseInt(skuId));
        return R.ok().setData(sku);
    }

    @GetMapping("/all")
    public R getAllSkuByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Sku> thePage = skuService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }
}
