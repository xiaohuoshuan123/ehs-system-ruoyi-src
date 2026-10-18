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
import org.dromara.system.domain.vo.SafetyDutyVo;
import org.dromara.system.domain.bo.SafetyDutyBo;
import org.dromara.system.service.ISafetyDutyService;
import org.dromara.common.core.domain.PageResult;

/**
 * SafetyDuty
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jgzr/safety_duty")
public class SafetyDutyController extends BaseController {

    private final ISafetyDutyService safetyDutyService;

    /**
     * 查询SafetyDuty列表
     */
    @SaCheckPermission("jgzr:safety_duty:list")
    @GetMapping("/list")
    public R<PageResult<SafetyDutyVo>> list(SafetyDutyBo bo, PageQuery pageQuery) {
        return R.ok(safetyDutyService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SafetyDuty列表
     */
    @SaCheckPermission("jgzr:safety_duty:export")
    @Log(title = "SafetyDuty", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SafetyDutyBo bo, HttpServletResponse response) {
        List<SafetyDutyVo> list = safetyDutyService.queryList(bo);
        ExcelBuilder.of(list, SafetyDutyVo.class).sheetName("SafetyDuty").toResponse(response);
    }

    /**
     * 获取SafetyDuty详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jgzr:safety_duty:query")
    @GetMapping("/{id}")
    public R<SafetyDutyVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(safetyDutyService.queryById(id));
    }

    /**
     * 新增SafetyDuty
     */
    @SaCheckPermission("jgzr:safety_duty:add")
    @Log(title = "SafetyDuty", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SafetyDutyBo bo) {
        return toAjax(safetyDutyService.insertByBo(bo));
    }

    /**
     * 修改SafetyDuty
     */
    @SaCheckPermission("jgzr:safety_duty:edit")
    @Log(title = "SafetyDuty", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SafetyDutyBo bo) {
        return toAjax(safetyDutyService.updateByBo(bo));
    }



    /**
     * 删除SafetyDuty
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jgzr:safety_duty:remove")
    @Log(title = "SafetyDuty", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(safetyDutyService.deleteWithValidByIds(List.of(ids), true));
    }
}
