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
import org.dromara.system.domain.vo.SafetyCostVo;
import org.dromara.system.domain.bo.SafetyCostBo;
import org.dromara.system.service.ISafetyCostService;
import org.dromara.common.core.domain.PageResult;

/**
 * SafetyCost
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jgfy/safety_cost")
public class SafetyCostController extends BaseController {

    private final ISafetyCostService safetyCostService;

    /**
     * 查询SafetyCost列表
     */
    @SaCheckPermission("jgfy:safety_cost:list")
    @GetMapping("/list")
    public R<PageResult<SafetyCostVo>> list(SafetyCostBo bo, PageQuery pageQuery) {
        return R.ok(safetyCostService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SafetyCost列表
     */
    @SaCheckPermission("jgfy:safety_cost:export")
    @Log(title = "SafetyCost", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SafetyCostBo bo, HttpServletResponse response) {
        List<SafetyCostVo> list = safetyCostService.queryList(bo);
        ExcelBuilder.of(list, SafetyCostVo.class).sheetName("SafetyCost").toResponse(response);
    }

    /**
     * 获取SafetyCost详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jgfy:safety_cost:query")
    @GetMapping("/{id}")
    public R<SafetyCostVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(safetyCostService.queryById(id));
    }

    /**
     * 新增SafetyCost
     */
    @SaCheckPermission("jgfy:safety_cost:add")
    @Log(title = "SafetyCost", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SafetyCostBo bo) {
        return toAjax(safetyCostService.insertByBo(bo));
    }

    /**
     * 修改SafetyCost
     */
    @SaCheckPermission("jgfy:safety_cost:edit")
    @Log(title = "SafetyCost", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SafetyCostBo bo) {
        return toAjax(safetyCostService.updateByBo(bo));
    }



    /**
     * 删除SafetyCost
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jgfy:safety_cost:remove")
    @Log(title = "SafetyCost", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(safetyCostService.deleteWithValidByIds(List.of(ids), true));
    }
}
