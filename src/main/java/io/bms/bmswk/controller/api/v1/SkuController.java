package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.param.SkuCreateParam;
import io.bms.bmswk.model.param.SkuUpdateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.ISkuService;
import io.bms.bmswk.util.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * item table controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@Validated
@RequestMapping("/api/v1/sku")
public class SkuController {

    @Autowired
    private ISkuService skuService;

    @GetMapping("/{skuId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    public R getSkuById(@PathVariable String skuId) {
        Sku sku = skuService.getById(Integer.parseInt(skuId));
        return R.ok().setData(sku);
    }

    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    public R getAllSkuByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Sku> thePage = skuService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    public R addOneSku(@RequestBody @Valid SkuCreateParam param) {

        Sku sku = BeanUtils.transformFrom(param, Sku.class);
        sku.setValid(true);

        skuService.save(sku);

        return R.ok();
    }

    @PutMapping("/{skuId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    public R editOneSku(@RequestBody @Valid SkuUpdateParam param, @PathVariable String skuId) {

        synchronized (this) {
            Sku sku = skuService.getById(Integer.parseInt(skuId));
            BeanUtils.updateProperties(param, sku);
            skuService.updateById(sku);
        }

        return R.ok();
    }

}
