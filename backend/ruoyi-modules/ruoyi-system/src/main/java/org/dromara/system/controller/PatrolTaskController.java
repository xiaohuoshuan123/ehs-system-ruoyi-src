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
import org.dromara.system.domain.vo.PatrolTaskVo;
import org.dromara.system.domain.bo.PatrolTaskBo;
import org.dromara.system.service.IPatrolTaskService;
import org.dromara.common.core.domain.PageResult;

/**
 * PatrolTask
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yfxc/patrol_task")
public class PatrolTaskController extends BaseController {

    private final IPatrolTaskService patrolTaskService;

    /**
     * 查询PatrolTask列表
     */
    @SaCheckPermission("yfxc:patrol_task:list")
    @GetMapping("/list")
    public R<PageResult<PatrolTaskVo>> list(PatrolTaskBo bo, PageQuery pageQuery) {
        return R.ok(patrolTaskService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出PatrolTask列表
     */
    @SaCheckPermission("yfxc:patrol_task:export")
    @Log(title = "PatrolTask", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PatrolTaskBo bo, HttpServletResponse response) {
        List<PatrolTaskVo> list = patrolTaskService.queryList(bo);
        ExcelBuilder.of(list, PatrolTaskVo.class).sheetName("PatrolTask").toResponse(response);
    }

    /**
     * 获取PatrolTask详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yfxc:patrol_task:query")
    @GetMapping("/{id}")
    public R<PatrolTaskVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(patrolTaskService.queryById(id));
    }

    /**
     * 新增PatrolTask
     */
    @SaCheckPermission("yfxc:patrol_task:add")
    @Log(title = "PatrolTask", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PatrolTaskBo bo) {
        return toAjax(patrolTaskService.insertByBo(bo));
    }

    /**
     * 修改PatrolTask
     */
    @SaCheckPermission("yfxc:patrol_task:edit")
    @Log(title = "PatrolTask", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PatrolTaskBo bo) {
        return toAjax(patrolTaskService.updateByBo(bo));
    }



    /**
     * 删除PatrolTask
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yfxc:patrol_task:remove")
    @Log(title = "PatrolTask", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(patrolTaskService.deleteWithValidByIds(List.of(ids), true));
    }
}
