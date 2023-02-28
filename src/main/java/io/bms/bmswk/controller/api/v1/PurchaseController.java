package io.bms.bmswk.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.bms.bmswk.model.entity.Purchase;
import io.bms.bmswk.model.support.R;
import io.bms.bmswk.service.IPurchaseService;
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
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @GetMapping("/{purchaseId}")
    public R getPurchaseById(@PathVariable String purchaseId) {
        return R.ok().setData(purchaseService.getById(Integer.parseInt(purchaseId)));
    }

    @GetMapping("/all")
    public R getPurchasesByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<Purchase> thePage = purchaseService.page(new Page<>(page, size));

        return R.ok().setData(thePage.getRecords());
    }

}
