package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Spu;
import io.bms.bmswk.model.param.SpuCreateParam;
import io.bms.bmswk.model.param.SpuUpdateParam;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.service.ISpuService;
import io.bms.bmswk.util.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 * product table controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@Validated
@RequestMapping("/api/v1/spu")
public class SpuController {

    @Autowired
    private ISpuService spuService;

    @GetMapping("/{spuId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    public R getSpuById(@PathVariable String spuId) {
        Spu spu = spuService.getById(Integer.parseInt(spuId));
        return R.ok().setData(spu);
    }

    @GetMapping("/all")
    @RequiresPermissions({SecurityConstant.INVENTORY_SEE_PERMISSION})
    public R getAllSpuByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Spu> thePage = spuService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

    @PostMapping("")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    public R addOneSpu(@RequestBody @Valid SpuCreateParam param) {
        Spu spu = new Spu();
        spu.setName(param.getName());
        spu.setCategoryId(param.getCategoryId());
        spu.setSaleable(param.getSaleable());
        spu.setValid(true);

        spuService.save(spu);

        return R.ok();
    }

    @PutMapping("/{spuId}")
    @RequiresPermissions({SecurityConstant.INVENTORY_MANAGE_PERMISSION})
    public R editOneSpu(@RequestBody @Valid SpuUpdateParam param, @PathVariable String spuId) {

        synchronized (this) {
            Integer id = Integer.parseInt(spuId);
            Spu spu = spuService.getById(id);
            BeanUtils.updateProperties(param, spu);

            spuService.updateById(spu);
        }

        return R.ok();
    }

}
