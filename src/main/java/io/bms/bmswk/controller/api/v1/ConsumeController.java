package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Consume;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/api/v1/consume")
public class ConsumeController {

    @Autowired
    private IConsumeService consumeService;

    @GetMapping("/{consumeId}")
    public R getConsumeById(@PathVariable String consumeId) {
        return R.ok().setData(consumeService.getById(Integer.parseInt(consumeId)));
    }

    @GetMapping("/all")
    public R getConsumesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Consume> thePage = consumeService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

}
