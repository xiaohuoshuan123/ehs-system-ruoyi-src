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
import org.dromara.system.domain.vo.BrscoreStandardVo;
import org.dromara.system.domain.bo.BrscoreStandardBo;
import org.dromara.system.service.IBrscoreStandardService;
import org.dromara.common.core.domain.PageResult;

/**
 * BrscoreStandard
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wztk/brscore_standard")
public class BrscoreStandardController extends BaseController {

    private final IBrscoreStandardService brscoreStandardService;

    /**
     * 查询BrscoreStandard列表
     */
    @SaCheckPermission("wztk:brscore_standard:list")
    @GetMapping("/list")
    public R<PageResult<BrscoreStandardVo>> list(BrscoreStandardBo bo, PageQuery pageQuery) {
        return R.ok(brscoreStandardService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出BrscoreStandard列表
     */
    @SaCheckPermission("wztk:brscore_standard:export")
    @Log(title = "BrscoreStandard", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BrscoreStandardBo bo, HttpServletResponse response) {
        List<BrscoreStandardVo> list = brscoreStandardService.queryList(bo);
        ExcelBuilder.of(list, BrscoreStandardVo.class).sheetName("BrscoreStandard").toResponse(response);
    }

    /**
     * 获取BrscoreStandard详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wztk:brscore_standard:query")
    @GetMapping("/{id}")
    public R<BrscoreStandardVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(brscoreStandardService.queryById(id));
    }

    /**
     * 新增BrscoreStandard
     */
    @SaCheckPermission("wztk:brscore_standard:add")
    @Log(title = "BrscoreStandard", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BrscoreStandardBo bo) {
        return toAjax(brscoreStandardService.insertByBo(bo));
    }

    /**
     * 修改BrscoreStandard
     */
    @SaCheckPermission("wztk:brscore_standard:edit")
    @Log(title = "BrscoreStandard", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BrscoreStandardBo bo) {
        return toAjax(brscoreStandardService.updateByBo(bo));
    }



    /**
     * 删除BrscoreStandard
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wztk:brscore_standard:remove")
    @Log(title = "BrscoreStandard", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(brscoreStandardService.deleteWithValidByIds(List.of(ids), true));
    }
}
