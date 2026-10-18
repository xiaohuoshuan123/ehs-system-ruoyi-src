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
import org.dromara.system.domain.vo.CompanyRegulationVo;
import org.dromara.system.domain.bo.CompanyRegulationBo;
import org.dromara.system.service.ICompanyRegulationService;
import org.dromara.common.core.domain.PageResult;

/**
 * CompanyRegulation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/zbfl/company_regulation")
public class CompanyRegulationController extends BaseController {

    private final ICompanyRegulationService companyRegulationService;

    /**
     * 查询CompanyRegulation列表
     */
    @SaCheckPermission("zbfl:company_regulation:list")
    @GetMapping("/list")
    public R<PageResult<CompanyRegulationVo>> list(CompanyRegulationBo bo, PageQuery pageQuery) {
        return R.ok(companyRegulationService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出CompanyRegulation列表
     */
    @SaCheckPermission("zbfl:company_regulation:export")
    @Log(title = "CompanyRegulation", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CompanyRegulationBo bo, HttpServletResponse response) {
        List<CompanyRegulationVo> list = companyRegulationService.queryList(bo);
        ExcelBuilder.of(list, CompanyRegulationVo.class).sheetName("CompanyRegulation").toResponse(response);
    }

    /**
     * 获取CompanyRegulation详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zbfl:company_regulation:query")
    @GetMapping("/{id}")
    public R<CompanyRegulationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(companyRegulationService.queryById(id));
    }

    /**
     * 新增CompanyRegulation
     */
    @SaCheckPermission("zbfl:company_regulation:add")
    @Log(title = "CompanyRegulation", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CompanyRegulationBo bo) {
        return toAjax(companyRegulationService.insertByBo(bo));
    }

    /**
     * 修改CompanyRegulation
     */
    @SaCheckPermission("zbfl:company_regulation:edit")
    @Log(title = "CompanyRegulation", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CompanyRegulationBo bo) {
        return toAjax(companyRegulationService.updateByBo(bo));
    }



    /**
     * 删除CompanyRegulation
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zbfl:company_regulation:remove")
    @Log(title = "CompanyRegulation", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(companyRegulationService.deleteWithValidByIds(List.of(ids), true));
    }
}
