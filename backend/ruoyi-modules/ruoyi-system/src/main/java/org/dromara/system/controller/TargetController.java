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
import org.dromara.system.domain.vo.TargetVo;
import org.dromara.system.domain.bo.TargetBo;
import org.dromara.system.service.ITargetService;
import org.dromara.common.core.domain.PageResult;

/**
 * Target
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/mbmb/target")
public class TargetController extends BaseController {

    private final ITargetService targetService;

    /**
     * 查询Target列表
     */
    @SaCheckPermission("mbmb:target:list")
    @GetMapping("/list")
    public R<PageResult<TargetVo>> list(TargetBo bo, PageQuery pageQuery) {
        return R.ok(targetService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Target列表
     */
    @SaCheckPermission("mbmb:target:export")
    @Log(title = "Target", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TargetBo bo, HttpServletResponse response) {
        List<TargetVo> list = targetService.queryList(bo);
        ExcelBuilder.of(list, TargetVo.class).sheetName("Target").toResponse(response);
    }

    /**
     * 获取Target详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("mbmb:target:query")
    @GetMapping("/{id}")
    public R<TargetVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(targetService.queryById(id));
    }

    /**
     * 新增Target
     */
    @SaCheckPermission("mbmb:target:add")
    @Log(title = "Target", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TargetBo bo) {
        return toAjax(targetService.insertByBo(bo));
    }

    /**
     * 修改Target
     */
    @SaCheckPermission("mbmb:target:edit")
    @Log(title = "Target", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TargetBo bo) {
        return toAjax(targetService.updateByBo(bo));
    }



    /**
     * 删除Target
     *
     * @param ids 主键串
     */
    @SaCheckPermission("mbmb:target:remove")
    @Log(title = "Target", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(targetService.deleteWithValidByIds(List.of(ids), true));
    }
}
