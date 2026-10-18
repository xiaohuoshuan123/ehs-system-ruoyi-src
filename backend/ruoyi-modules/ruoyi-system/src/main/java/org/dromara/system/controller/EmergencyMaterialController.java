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
import org.dromara.system.domain.vo.EmergencyMaterialVo;
import org.dromara.system.domain.bo.EmergencyMaterialBo;
import org.dromara.system.service.IEmergencyMaterialService;
import org.dromara.common.core.domain.PageResult;

/**
 * EmergencyMaterial
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yjwz/emergency_material")
public class EmergencyMaterialController extends BaseController {

    private final IEmergencyMaterialService emergencyMaterialService;

    /**
     * 查询EmergencyMaterial列表
     */
    @SaCheckPermission("yjwz:emergency_material:list")
    @GetMapping("/list")
    public R<PageResult<EmergencyMaterialVo>> list(EmergencyMaterialBo bo, PageQuery pageQuery) {
        return R.ok(emergencyMaterialService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EmergencyMaterial列表
     */
    @SaCheckPermission("yjwz:emergency_material:export")
    @Log(title = "EmergencyMaterial", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EmergencyMaterialBo bo, HttpServletResponse response) {
        List<EmergencyMaterialVo> list = emergencyMaterialService.queryList(bo);
        ExcelBuilder.of(list, EmergencyMaterialVo.class).sheetName("EmergencyMaterial").toResponse(response);
    }

    /**
     * 获取EmergencyMaterial详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yjwz:emergency_material:query")
    @GetMapping("/{id}")
    public R<EmergencyMaterialVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(emergencyMaterialService.queryById(id));
    }

    /**
     * 新增EmergencyMaterial
     */
    @SaCheckPermission("yjwz:emergency_material:add")
    @Log(title = "EmergencyMaterial", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EmergencyMaterialBo bo) {
        return toAjax(emergencyMaterialService.insertByBo(bo));
    }

    /**
     * 修改EmergencyMaterial
     */
    @SaCheckPermission("yjwz:emergency_material:edit")
    @Log(title = "EmergencyMaterial", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EmergencyMaterialBo bo) {
        return toAjax(emergencyMaterialService.updateByBo(bo));
    }



    /**
     * 删除EmergencyMaterial
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yjwz:emergency_material:remove")
    @Log(title = "EmergencyMaterial", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(emergencyMaterialService.deleteWithValidByIds(List.of(ids), true));
    }
}
