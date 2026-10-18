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
import org.dromara.system.domain.vo.SafetyStudyDtlVo;
import org.dromara.system.domain.bo.SafetyStudyDtlBo;
import org.dromara.system.service.ISafetyStudyDtlService;
import org.dromara.common.core.domain.PageResult;

/**
 * SafetyStudyDtl
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxxx/safety_study_dtl")
public class SafetyStudyDtlController extends BaseController {

    private final ISafetyStudyDtlService safetyStudyDtlService;

    /**
     * 查询SafetyStudyDtl列表
     */
    @SaCheckPermission("pxxx:safety_study_dtl:list")
    @GetMapping("/list")
    public R<PageResult<SafetyStudyDtlVo>> list(SafetyStudyDtlBo bo, PageQuery pageQuery) {
        return R.ok(safetyStudyDtlService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SafetyStudyDtl列表
     */
    @SaCheckPermission("pxxx:safety_study_dtl:export")
    @Log(title = "SafetyStudyDtl", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SafetyStudyDtlBo bo, HttpServletResponse response) {
        List<SafetyStudyDtlVo> list = safetyStudyDtlService.queryList(bo);
        ExcelBuilder.of(list, SafetyStudyDtlVo.class).sheetName("SafetyStudyDtl").toResponse(response);
    }

    /**
     * 获取SafetyStudyDtl详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxxx:safety_study_dtl:query")
    @GetMapping("/{id}")
    public R<SafetyStudyDtlVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(safetyStudyDtlService.queryById(id));
    }

    /**
     * 新增SafetyStudyDtl
     */
    @SaCheckPermission("pxxx:safety_study_dtl:add")
    @Log(title = "SafetyStudyDtl", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SafetyStudyDtlBo bo) {
        return toAjax(safetyStudyDtlService.insertByBo(bo));
    }

    /**
     * 修改SafetyStudyDtl
     */
    @SaCheckPermission("pxxx:safety_study_dtl:edit")
    @Log(title = "SafetyStudyDtl", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SafetyStudyDtlBo bo) {
        return toAjax(safetyStudyDtlService.updateByBo(bo));
    }



    /**
     * 删除SafetyStudyDtl
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxxx:safety_study_dtl:remove")
    @Log(title = "SafetyStudyDtl", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(safetyStudyDtlService.deleteWithValidByIds(List.of(ids), true));
    }
}
