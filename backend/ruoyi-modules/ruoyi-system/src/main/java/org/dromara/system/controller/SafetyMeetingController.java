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
import org.dromara.system.domain.vo.SafetyMeetingVo;
import org.dromara.system.domain.bo.SafetyMeetingBo;
import org.dromara.system.service.ISafetyMeetingService;
import org.dromara.common.core.domain.PageResult;

/**
 * SafetyMeeting
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jgaw/safety_meeting")
public class SafetyMeetingController extends BaseController {

    private final ISafetyMeetingService safetyMeetingService;

    /**
     * 查询SafetyMeeting列表
     */
    @SaCheckPermission("jgaw:safety_meeting:list")
    @GetMapping("/list")
    public R<PageResult<SafetyMeetingVo>> list(SafetyMeetingBo bo, PageQuery pageQuery) {
        return R.ok(safetyMeetingService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SafetyMeeting列表
     */
    @SaCheckPermission("jgaw:safety_meeting:export")
    @Log(title = "SafetyMeeting", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SafetyMeetingBo bo, HttpServletResponse response) {
        List<SafetyMeetingVo> list = safetyMeetingService.queryList(bo);
        ExcelBuilder.of(list, SafetyMeetingVo.class).sheetName("SafetyMeeting").toResponse(response);
    }

    /**
     * 获取SafetyMeeting详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jgaw:safety_meeting:query")
    @GetMapping("/{id}")
    public R<SafetyMeetingVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(safetyMeetingService.queryById(id));
    }

    /**
     * 新增SafetyMeeting
     */
    @SaCheckPermission("jgaw:safety_meeting:add")
    @Log(title = "SafetyMeeting", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SafetyMeetingBo bo) {
        return toAjax(safetyMeetingService.insertByBo(bo));
    }

    /**
     * 修改SafetyMeeting
     */
    @SaCheckPermission("jgaw:safety_meeting:edit")
    @Log(title = "SafetyMeeting", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SafetyMeetingBo bo) {
        return toAjax(safetyMeetingService.updateByBo(bo));
    }



    /**
     * 删除SafetyMeeting
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jgaw:safety_meeting:remove")
    @Log(title = "SafetyMeeting", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(safetyMeetingService.deleteWithValidByIds(List.of(ids), true));
    }
}
