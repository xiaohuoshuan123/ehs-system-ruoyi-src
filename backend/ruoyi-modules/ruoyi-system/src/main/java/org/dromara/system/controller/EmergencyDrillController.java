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
import org.dromara.system.domain.vo.EmergencyDrillVo;
import org.dromara.system.domain.bo.EmergencyDrillBo;
import org.dromara.system.service.IEmergencyDrillService;
import org.dromara.common.core.domain.PageResult;

/**
 * EmergencyDrill
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yjep/emergency_drill")
public class EmergencyDrillController extends BaseController {

    private final IEmergencyDrillService emergencyDrillService;

    /**
     * 查询EmergencyDrill列表
     */
    @SaCheckPermission("yjep:emergency_drill:list")
    @GetMapping("/list")
    public R<PageResult<EmergencyDrillVo>> list(EmergencyDrillBo bo, PageQuery pageQuery) {
        return R.ok(emergencyDrillService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EmergencyDrill列表
     */
    @SaCheckPermission("yjep:emergency_drill:export")
    @Log(title = "EmergencyDrill", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EmergencyDrillBo bo, HttpServletResponse response) {
        List<EmergencyDrillVo> list = emergencyDrillService.queryList(bo);
        ExcelBuilder.of(list, EmergencyDrillVo.class).sheetName("EmergencyDrill").toResponse(response);
    }

    /**
     * 获取EmergencyDrill详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yjep:emergency_drill:query")
    @GetMapping("/{id}")
    public R<EmergencyDrillVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(emergencyDrillService.queryById(id));
    }

    /**
     * 新增EmergencyDrill
     */
    @SaCheckPermission("yjep:emergency_drill:add")
    @Log(title = "EmergencyDrill", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EmergencyDrillBo bo) {
        return toAjax(emergencyDrillService.insertByBo(bo));
    }

    /**
     * 修改EmergencyDrill
     */
    @SaCheckPermission("yjep:emergency_drill:edit")
    @Log(title = "EmergencyDrill", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EmergencyDrillBo bo) {
        return toAjax(emergencyDrillService.updateByBo(bo));
    }



    /**
     * 删除EmergencyDrill
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yjep:emergency_drill:remove")
    @Log(title = "EmergencyDrill", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(emergencyDrillService.deleteWithValidByIds(List.of(ids), true));
    }
}
