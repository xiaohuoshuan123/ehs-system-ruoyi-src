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
import org.dromara.system.domain.vo.PlanVo;
import org.dromara.system.domain.bo.PlanBo;
import org.dromara.system.service.IPlanService;
import org.dromara.common.core.domain.PageResult;

/**
 * Plan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/mbgh/plan")
public class PlanController extends BaseController {

    private final IPlanService planService;

    /**
     * 查询Plan列表
     */
    @SaCheckPermission("mbgh:plan:list")
    @GetMapping("/list")
    public R<PageResult<PlanVo>> list(PlanBo bo, PageQuery pageQuery) {
        return R.ok(planService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Plan列表
     */
    @SaCheckPermission("mbgh:plan:export")
    @Log(title = "Plan", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlanBo bo, HttpServletResponse response) {
        List<PlanVo> list = planService.queryList(bo);
        ExcelBuilder.of(list, PlanVo.class).sheetName("Plan").toResponse(response);
    }

    /**
     * 获取Plan详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("mbgh:plan:query")
    @GetMapping("/{id}")
    public R<PlanVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(planService.queryById(id));
    }

    /**
     * 新增Plan
     */
    @SaCheckPermission("mbgh:plan:add")
    @Log(title = "Plan", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlanBo bo) {
        return toAjax(planService.insertByBo(bo));
    }

    /**
     * 修改Plan
     */
    @SaCheckPermission("mbgh:plan:edit")
    @Log(title = "Plan", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlanBo bo) {
        return toAjax(planService.updateByBo(bo));
    }



    /**
     * 删除Plan
     *
     * @param ids 主键串
     */
    @SaCheckPermission("mbgh:plan:remove")
    @Log(title = "Plan", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(planService.deleteWithValidByIds(List.of(ids), true));
    }
}
