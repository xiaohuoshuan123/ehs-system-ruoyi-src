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
import org.dromara.system.domain.vo.SafetyStudyMstVo;
import org.dromara.system.domain.bo.SafetyStudyMstBo;
import org.dromara.system.service.ISafetyStudyMstService;
import org.dromara.common.core.domain.PageResult;

/**
 * SafetyStudyMst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxxx/safety_study_mst")
public class SafetyStudyMstController extends BaseController {

    private final ISafetyStudyMstService safetyStudyMstService;

    /**
     * 查询SafetyStudyMst列表
     */
    @SaCheckPermission("pxxx:safety_study_mst:list")
    @GetMapping("/list")
    public R<PageResult<SafetyStudyMstVo>> list(SafetyStudyMstBo bo, PageQuery pageQuery) {
        return R.ok(safetyStudyMstService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SafetyStudyMst列表
     */
    @SaCheckPermission("pxxx:safety_study_mst:export")
    @Log(title = "SafetyStudyMst", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SafetyStudyMstBo bo, HttpServletResponse response) {
        List<SafetyStudyMstVo> list = safetyStudyMstService.queryList(bo);
        ExcelBuilder.of(list, SafetyStudyMstVo.class).sheetName("SafetyStudyMst").toResponse(response);
    }

    /**
     * 获取SafetyStudyMst详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxxx:safety_study_mst:query")
    @GetMapping("/{id}")
    public R<SafetyStudyMstVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(safetyStudyMstService.queryById(id));
    }

    /**
     * 新增SafetyStudyMst
     */
    @SaCheckPermission("pxxx:safety_study_mst:add")
    @Log(title = "SafetyStudyMst", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SafetyStudyMstBo bo) {
        return toAjax(safetyStudyMstService.insertByBo(bo));
    }

    /**
     * 修改SafetyStudyMst
     */
    @SaCheckPermission("pxxx:safety_study_mst:edit")
    @Log(title = "SafetyStudyMst", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SafetyStudyMstBo bo) {
        return toAjax(safetyStudyMstService.updateByBo(bo));
    }



    /**
     * 删除SafetyStudyMst
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxxx:safety_study_mst:remove")
    @Log(title = "SafetyStudyMst", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(safetyStudyMstService.deleteWithValidByIds(List.of(ids), true));
    }
}
