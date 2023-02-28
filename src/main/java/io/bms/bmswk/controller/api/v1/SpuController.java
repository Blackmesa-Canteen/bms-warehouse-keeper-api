package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Spu;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * product table controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/api/v1/spu")
public class SpuController {

    @Autowired
    private ISpuService spuService;

    @GetMapping("/{spuId}")
    public R getSpuById(@PathVariable String spuId) {
        Spu spu = spuService.getById(Integer.parseInt(spuId));
        return R.ok().setData(spu);
    }

    @GetMapping("/all")
    public R getAllSpuByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Spu> thePage = spuService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }
}
