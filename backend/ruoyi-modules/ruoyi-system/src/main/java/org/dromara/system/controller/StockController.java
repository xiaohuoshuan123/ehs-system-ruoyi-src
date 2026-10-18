package org.dromara.system.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.redis.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelBuilder;
import org.dromara.system.domain.vo.StockVo;
import org.dromara.system.domain.bo.StockBo;
import org.dromara.system.service.IStockService;
import org.dromara.common.core.domain.PageResult;

/**
 * Stock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whtz/stock")
public class StockController extends BaseController {

    private final IStockService stockService;

    /**
     * 查询Stock列表
     */
    @SaCheckPermission("whtz:stock:list")
    @GetMapping("/list")
    public R<PageResult<StockVo>> list(StockBo bo, PageQuery pageQuery) {
        return R.ok(stockService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Stock列表
     */
    @SaCheckPermission("whtz:stock:export")
    @Log(title = "Stock", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StockBo bo, HttpServletResponse response) {
        List<StockVo> list = stockService.queryList(bo);
        ExcelBuilder.of(list, StockVo.class).sheetName("Stock").toResponse(response);
    }

    /**
     * 获取Stock详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whtz:stock:query")
    @GetMapping("/{id}")
    public R<StockVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(stockService.queryById(id));
    }

    /**
     * 新增Stock
     */
    @SaCheckPermission("whtz:stock:add")
    @Log(title = "Stock", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StockBo bo) {
        return toAjax(stockService.insertByBo(bo));
    }

    /**
     * 修改Stock
     */
    @SaCheckPermission("whtz:stock:edit")
    @Log(title = "Stock", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StockBo bo) {
        return toAjax(stockService.updateByBo(bo));
    }



    /**
     * 删除Stock
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whtz:stock:remove")
    @Log(title = "Stock", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(stockService.deleteWithValidByIds(List.of(ids), true));
    }
}
