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
import org.dromara.system.domain.vo.StandardDeptVo;
import org.dromara.system.domain.bo.StandardDeptBo;
import org.dromara.system.service.IStandardDeptService;
import org.dromara.common.core.domain.PageResult;

/**
 * StandardDept
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lzbz/standard_dept")
public class StandardDeptController extends BaseController {

    private final IStandardDeptService standardDeptService;

    /**
     * 查询StandardDept列表
     */
    @SaCheckPermission("lzbz:standard_dept:list")
    @GetMapping("/list")
    public R<PageResult<StandardDeptVo>> list(StandardDeptBo bo, PageQuery pageQuery) {
        return R.ok(standardDeptService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出StandardDept列表
     */
    @SaCheckPermission("lzbz:standard_dept:export")
    @Log(title = "StandardDept", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StandardDeptBo bo, HttpServletResponse response) {
        List<StandardDeptVo> list = standardDeptService.queryList(bo);
        ExcelBuilder.of(list, StandardDeptVo.class).sheetName("StandardDept").toResponse(response);
    }

    /**
     * 获取StandardDept详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lzbz:standard_dept:query")
    @GetMapping("/{id}")
    public R<StandardDeptVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(standardDeptService.queryById(id));
    }

    /**
     * 新增StandardDept
     */
    @SaCheckPermission("lzbz:standard_dept:add")
    @Log(title = "StandardDept", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StandardDeptBo bo) {
        return toAjax(standardDeptService.insertByBo(bo));
    }

    /**
     * 修改StandardDept
     */
    @SaCheckPermission("lzbz:standard_dept:edit")
    @Log(title = "StandardDept", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StandardDeptBo bo) {
        return toAjax(standardDeptService.updateByBo(bo));
    }



    /**
     * 删除StandardDept
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lzbz:standard_dept:remove")
    @Log(title = "StandardDept", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(standardDeptService.deleteWithValidByIds(List.of(ids), true));
    }
}
