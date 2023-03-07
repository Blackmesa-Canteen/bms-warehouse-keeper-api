package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Sku;
import io.bms.bmswk.model.param.SkuCreateParam;
import io.bms.bmswk.model.param.SkuUpdateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.ISkuService;
import io.bms.bmswk.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@Api(tags = "Stock Keeping Unit APIs")
public class SkuController {

    private final ISkuService skuService;

    public SkuController(ISkuService skuService) {
        this.skuService = skuService;
    }

    @GetMapping("/{skuId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Get sku by id")
    public R getSkuById(@PathVariable String skuId) {
        Sku sku = skuService.getById(Integer.parseInt(skuId));
        return R.ok().setData(sku);
    }

    @GetMapping("/query")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Query skus under spu id by page")
    public R querySkusBySpuId(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size, @RequestParam(value = "spuId") Integer spuId) {
        List<Sku> skus = skuService.getSkusBySpuId(page, size, spuId);

        return R.ok().setData(skus);
    }

    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    @ApiOperation("Find all skus by page")
    public R getAllSkuByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Sku> thePage = skuService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation("Add one stock keeping product info in system")
    public R addOneSku(@RequestBody @Valid SkuCreateParam param) {

        Sku sku = BeanUtils.transformFrom(param, Sku.class);
        sku.setValid(true);

        skuService.save(sku);

        return R.ok();
    }

    @PutMapping("/{skuId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation("Edit existing sku")
    public R editOneSku(@RequestBody @Valid SkuUpdateParam param, @PathVariable String skuId) {

        synchronized (this) {
            Sku sku = skuService.getById(Integer.parseInt(skuId));
            BeanUtils.updateProperties(param, sku);
            skuService.updateById(sku);
        }

        return R.ok();
    }

    @DeleteMapping("/{skuId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    @ApiOperation(value = "Find permissions by role name",
            notes = "Delete one sku. Delete can be performed only if the sku is unused.")
    public R deleteSkuById(@PathVariable Integer skuId) {
        skuService.removeById(skuId);
        return R.ok();
    }

}
