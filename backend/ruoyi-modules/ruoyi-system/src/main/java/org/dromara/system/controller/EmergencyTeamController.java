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
import org.dromara.system.domain.vo.EmergencyTeamVo;
import org.dromara.system.domain.bo.EmergencyTeamBo;
import org.dromara.system.service.IEmergencyTeamService;
import org.dromara.common.core.domain.PageResult;

/**
 * EmergencyTeam
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yjtm/emergency_team")
public class EmergencyTeamController extends BaseController {

    private final IEmergencyTeamService emergencyTeamService;

    /**
     * 查询EmergencyTeam列表
     */
    @SaCheckPermission("yjtm:emergency_team:list")
    @GetMapping("/list")
    public R<PageResult<EmergencyTeamVo>> list(EmergencyTeamBo bo, PageQuery pageQuery) {
        return R.ok(emergencyTeamService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EmergencyTeam列表
     */
    @SaCheckPermission("yjtm:emergency_team:export")
    @Log(title = "EmergencyTeam", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EmergencyTeamBo bo, HttpServletResponse response) {
        List<EmergencyTeamVo> list = emergencyTeamService.queryList(bo);
        ExcelBuilder.of(list, EmergencyTeamVo.class).sheetName("EmergencyTeam").toResponse(response);
    }

    /**
     * 获取EmergencyTeam详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yjtm:emergency_team:query")
    @GetMapping("/{id}")
    public R<EmergencyTeamVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(emergencyTeamService.queryById(id));
    }

    /**
     * 新增EmergencyTeam
     */
    @SaCheckPermission("yjtm:emergency_team:add")
    @Log(title = "EmergencyTeam", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EmergencyTeamBo bo) {
        return toAjax(emergencyTeamService.insertByBo(bo));
    }

    /**
     * 修改EmergencyTeam
     */
    @SaCheckPermission("yjtm:emergency_team:edit")
    @Log(title = "EmergencyTeam", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EmergencyTeamBo bo) {
        return toAjax(emergencyTeamService.updateByBo(bo));
    }



    /**
     * 删除EmergencyTeam
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yjtm:emergency_team:remove")
    @Log(title = "EmergencyTeam", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(emergencyTeamService.deleteWithValidByIds(List.of(ids), true));
    }
}
