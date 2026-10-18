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
import org.dromara.system.domain.vo.SpecialWorkVo;
import org.dromara.system.domain.bo.SpecialWorkBo;
import org.dromara.system.service.ISpecialWorkService;
import org.dromara.common.core.domain.PageResult;

/**
 * SpecialWork
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxzs/special_work")
public class SpecialWorkController extends BaseController {

    private final ISpecialWorkService specialWorkService;

    /**
     * 查询SpecialWork列表
     */
    @SaCheckPermission("pxzs:special_work:list")
    @GetMapping("/list")
    public R<PageResult<SpecialWorkVo>> list(SpecialWorkBo bo, PageQuery pageQuery) {
        return R.ok(specialWorkService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SpecialWork列表
     */
    @SaCheckPermission("pxzs:special_work:export")
    @Log(title = "SpecialWork", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SpecialWorkBo bo, HttpServletResponse response) {
        List<SpecialWorkVo> list = specialWorkService.queryList(bo);
        ExcelBuilder.of(list, SpecialWorkVo.class).sheetName("SpecialWork").toResponse(response);
    }

    /**
     * 获取SpecialWork详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxzs:special_work:query")
    @GetMapping("/{id}")
    public R<SpecialWorkVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(specialWorkService.queryById(id));
    }

    /**
     * 新增SpecialWork
     */
    @SaCheckPermission("pxzs:special_work:add")
    @Log(title = "SpecialWork", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SpecialWorkBo bo) {
        return toAjax(specialWorkService.insertByBo(bo));
    }

    /**
     * 修改SpecialWork
     */
    @SaCheckPermission("pxzs:special_work:edit")
    @Log(title = "SpecialWork", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SpecialWorkBo bo) {
        return toAjax(specialWorkService.updateByBo(bo));
    }



    /**
     * 删除SpecialWork
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxzs:special_work:remove")
    @Log(title = "SpecialWork", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(specialWorkService.deleteWithValidByIds(List.of(ids), true));
    }
}
