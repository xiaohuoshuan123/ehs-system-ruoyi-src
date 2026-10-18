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
import org.dromara.system.domain.vo.OutstockVo;
import org.dromara.system.domain.bo.OutstockBo;
import org.dromara.system.service.IOutstockService;
import org.dromara.common.core.domain.PageResult;

/**
 * Outstock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whtz/outstock")
public class OutstockController extends BaseController {

    private final IOutstockService outstockService;

    /**
     * 查询Outstock列表
     */
    @SaCheckPermission("whtz:outstock:list")
    @GetMapping("/list")
    public R<PageResult<OutstockVo>> list(OutstockBo bo, PageQuery pageQuery) {
        return R.ok(outstockService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Outstock列表
     */
    @SaCheckPermission("whtz:outstock:export")
    @Log(title = "Outstock", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OutstockBo bo, HttpServletResponse response) {
        List<OutstockVo> list = outstockService.queryList(bo);
        ExcelBuilder.of(list, OutstockVo.class).sheetName("Outstock").toResponse(response);
    }

    /**
     * 获取Outstock详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whtz:outstock:query")
    @GetMapping("/{id}")
    public R<OutstockVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(outstockService.queryById(id));
    }

    /**
     * 新增Outstock
     */
    @SaCheckPermission("whtz:outstock:add")
    @Log(title = "Outstock", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OutstockBo bo) {
        return toAjax(outstockService.insertByBo(bo));
    }

    /**
     * 修改Outstock
     */
    @SaCheckPermission("whtz:outstock:edit")
    @Log(title = "Outstock", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OutstockBo bo) {
        return toAjax(outstockService.updateByBo(bo));
    }



    /**
     * 删除Outstock
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whtz:outstock:remove")
    @Log(title = "Outstock", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(outstockService.deleteWithValidByIds(List.of(ids), true));
    }
}
