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
import org.dromara.system.domain.vo.EmergencyEquipmentVo;
import org.dromara.system.domain.bo.EmergencyEquipmentBo;
import org.dromara.system.service.IEmergencyEquipmentService;
import org.dromara.common.core.domain.PageResult;

/**
 * EmergencyEquipment
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yfqc/emergency_equipment")
public class EmergencyEquipmentController extends BaseController {

    private final IEmergencyEquipmentService emergencyEquipmentService;

    /**
     * 查询EmergencyEquipment列表
     */
    @SaCheckPermission("yfqc:emergency_equipment:list")
    @GetMapping("/list")
    public R<PageResult<EmergencyEquipmentVo>> list(EmergencyEquipmentBo bo, PageQuery pageQuery) {
        return R.ok(emergencyEquipmentService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EmergencyEquipment列表
     */
    @SaCheckPermission("yfqc:emergency_equipment:export")
    @Log(title = "EmergencyEquipment", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EmergencyEquipmentBo bo, HttpServletResponse response) {
        List<EmergencyEquipmentVo> list = emergencyEquipmentService.queryList(bo);
        ExcelBuilder.of(list, EmergencyEquipmentVo.class).sheetName("EmergencyEquipment").toResponse(response);
    }

    /**
     * 获取EmergencyEquipment详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yfqc:emergency_equipment:query")
    @GetMapping("/{id}")
    public R<EmergencyEquipmentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(emergencyEquipmentService.queryById(id));
    }

    /**
     * 新增EmergencyEquipment
     */
    @SaCheckPermission("yfqc:emergency_equipment:add")
    @Log(title = "EmergencyEquipment", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EmergencyEquipmentBo bo) {
        return toAjax(emergencyEquipmentService.insertByBo(bo));
    }

    /**
     * 修改EmergencyEquipment
     */
    @SaCheckPermission("yfqc:emergency_equipment:edit")
    @Log(title = "EmergencyEquipment", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EmergencyEquipmentBo bo) {
        return toAjax(emergencyEquipmentService.updateByBo(bo));
    }



    /**
     * 删除EmergencyEquipment
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yfqc:emergency_equipment:remove")
    @Log(title = "EmergencyEquipment", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(emergencyEquipmentService.deleteWithValidByIds(List.of(ids), true));
    }
}
