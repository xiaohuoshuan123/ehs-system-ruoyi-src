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
import org.dromara.system.domain.vo.EmergencyPlanVo;
import org.dromara.system.domain.bo.EmergencyPlanBo;
import org.dromara.system.service.IEmergencyPlanService;
import org.dromara.common.core.domain.PageResult;

/**
 * EmergencyPlan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yjep/emergency_plan")
public class EmergencyPlanController extends BaseController {

    private final IEmergencyPlanService emergencyPlanService;

    /**
     * 查询EmergencyPlan列表
     */
    @SaCheckPermission("yjep:emergency_plan:list")
    @GetMapping("/list")
    public R<PageResult<EmergencyPlanVo>> list(EmergencyPlanBo bo, PageQuery pageQuery) {
        return R.ok(emergencyPlanService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EmergencyPlan列表
     */
    @SaCheckPermission("yjep:emergency_plan:export")
    @Log(title = "EmergencyPlan", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EmergencyPlanBo bo, HttpServletResponse response) {
        List<EmergencyPlanVo> list = emergencyPlanService.queryList(bo);
        ExcelBuilder.of(list, EmergencyPlanVo.class).sheetName("EmergencyPlan").toResponse(response);
    }

    /**
     * 获取EmergencyPlan详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yjep:emergency_plan:query")
    @GetMapping("/{id}")
    public R<EmergencyPlanVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(emergencyPlanService.queryById(id));
    }

    /**
     * 新增EmergencyPlan
     */
    @SaCheckPermission("yjep:emergency_plan:add")
    @Log(title = "EmergencyPlan", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EmergencyPlanBo bo) {
        return toAjax(emergencyPlanService.insertByBo(bo));
    }

    /**
     * 修改EmergencyPlan
     */
    @SaCheckPermission("yjep:emergency_plan:edit")
    @Log(title = "EmergencyPlan", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EmergencyPlanBo bo) {
        return toAjax(emergencyPlanService.updateByBo(bo));
    }



    /**
     * 删除EmergencyPlan
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yjep:emergency_plan:remove")
    @Log(title = "EmergencyPlan", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(emergencyPlanService.deleteWithValidByIds(List.of(ids), true));
    }
}
