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
import org.dromara.system.domain.vo.EmergencyPointVo;
import org.dromara.system.domain.bo.EmergencyPointBo;
import org.dromara.system.service.IEmergencyPointService;
import org.dromara.common.core.domain.PageResult;

/**
 * EmergencyPoint
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yfdh/emergency_point")
public class EmergencyPointController extends BaseController {

    private final IEmergencyPointService emergencyPointService;

    /**
     * 查询EmergencyPoint列表
     */
    @SaCheckPermission("yfdh:emergency_point:list")
    @GetMapping("/list")
    public R<PageResult<EmergencyPointVo>> list(EmergencyPointBo bo, PageQuery pageQuery) {
        return R.ok(emergencyPointService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EmergencyPoint列表
     */
    @SaCheckPermission("yfdh:emergency_point:export")
    @Log(title = "EmergencyPoint", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EmergencyPointBo bo, HttpServletResponse response) {
        List<EmergencyPointVo> list = emergencyPointService.queryList(bo);
        ExcelBuilder.of(list, EmergencyPointVo.class).sheetName("EmergencyPoint").toResponse(response);
    }

    /**
     * 获取EmergencyPoint详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yfdh:emergency_point:query")
    @GetMapping("/{id}")
    public R<EmergencyPointVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(emergencyPointService.queryById(id));
    }

    /**
     * 新增EmergencyPoint
     */
    @SaCheckPermission("yfdh:emergency_point:add")
    @Log(title = "EmergencyPoint", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EmergencyPointBo bo) {
        return toAjax(emergencyPointService.insertByBo(bo));
    }

    /**
     * 修改EmergencyPoint
     */
    @SaCheckPermission("yfdh:emergency_point:edit")
    @Log(title = "EmergencyPoint", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EmergencyPointBo bo) {
        return toAjax(emergencyPointService.updateByBo(bo));
    }



    /**
     * 删除EmergencyPoint
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yfdh:emergency_point:remove")
    @Log(title = "EmergencyPoint", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(emergencyPointService.deleteWithValidByIds(List.of(ids), true));
    }
}
