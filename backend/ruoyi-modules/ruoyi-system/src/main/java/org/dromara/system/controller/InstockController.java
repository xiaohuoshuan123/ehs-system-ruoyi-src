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
import org.dromara.system.domain.vo.InstockVo;
import org.dromara.system.domain.bo.InstockBo;
import org.dromara.system.service.IInstockService;
import org.dromara.common.core.domain.PageResult;

/**
 * Instock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whtz/instock")
public class InstockController extends BaseController {

    private final IInstockService instockService;

    /**
     * 查询Instock列表
     */
    @SaCheckPermission("whtz:instock:list")
    @GetMapping("/list")
    public R<PageResult<InstockVo>> list(InstockBo bo, PageQuery pageQuery) {
        return R.ok(instockService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Instock列表
     */
    @SaCheckPermission("whtz:instock:export")
    @Log(title = "Instock", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(InstockBo bo, HttpServletResponse response) {
        List<InstockVo> list = instockService.queryList(bo);
        ExcelBuilder.of(list, InstockVo.class).sheetName("Instock").toResponse(response);
    }

    /**
     * 获取Instock详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whtz:instock:query")
    @GetMapping("/{id}")
    public R<InstockVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(instockService.queryById(id));
    }

    /**
     * 新增Instock
     */
    @SaCheckPermission("whtz:instock:add")
    @Log(title = "Instock", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody InstockBo bo) {
        return toAjax(instockService.insertByBo(bo));
    }

    /**
     * 修改Instock
     */
    @SaCheckPermission("whtz:instock:edit")
    @Log(title = "Instock", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody InstockBo bo) {
        return toAjax(instockService.updateByBo(bo));
    }



    /**
     * 删除Instock
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whtz:instock:remove")
    @Log(title = "Instock", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(instockService.deleteWithValidByIds(List.of(ids), true));
    }
}
