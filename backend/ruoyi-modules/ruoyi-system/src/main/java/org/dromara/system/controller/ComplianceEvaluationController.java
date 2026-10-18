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
import org.dromara.system.domain.vo.ComplianceEvaluationVo;
import org.dromara.system.domain.bo.ComplianceEvaluationBo;
import org.dromara.system.service.IComplianceEvaluationService;
import org.dromara.common.core.domain.PageResult;

/**
 * ComplianceEvaluation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lzbz/compliance_evaluation")
public class ComplianceEvaluationController extends BaseController {

    private final IComplianceEvaluationService complianceEvaluationService;

    /**
     * 查询ComplianceEvaluation列表
     */
    @SaCheckPermission("lzbz:compliance_evaluation:list")
    @GetMapping("/list")
    public R<PageResult<ComplianceEvaluationVo>> list(ComplianceEvaluationBo bo, PageQuery pageQuery) {
        return R.ok(complianceEvaluationService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出ComplianceEvaluation列表
     */
    @SaCheckPermission("lzbz:compliance_evaluation:export")
    @Log(title = "ComplianceEvaluation", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ComplianceEvaluationBo bo, HttpServletResponse response) {
        List<ComplianceEvaluationVo> list = complianceEvaluationService.queryList(bo);
        ExcelBuilder.of(list, ComplianceEvaluationVo.class).sheetName("ComplianceEvaluation").toResponse(response);
    }

    /**
     * 获取ComplianceEvaluation详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lzbz:compliance_evaluation:query")
    @GetMapping("/{id}")
    public R<ComplianceEvaluationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(complianceEvaluationService.queryById(id));
    }

    /**
     * 新增ComplianceEvaluation
     */
    @SaCheckPermission("lzbz:compliance_evaluation:add")
    @Log(title = "ComplianceEvaluation", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ComplianceEvaluationBo bo) {
        return toAjax(complianceEvaluationService.insertByBo(bo));
    }

    /**
     * 修改ComplianceEvaluation
     */
    @SaCheckPermission("lzbz:compliance_evaluation:edit")
    @Log(title = "ComplianceEvaluation", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ComplianceEvaluationBo bo) {
        return toAjax(complianceEvaluationService.updateByBo(bo));
    }



    /**
     * 删除ComplianceEvaluation
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lzbz:compliance_evaluation:remove")
    @Log(title = "ComplianceEvaluation", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(complianceEvaluationService.deleteWithValidByIds(List.of(ids), true));
    }
}
