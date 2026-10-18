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
import org.dromara.system.domain.vo.MsdsVo;
import org.dromara.system.domain.bo.MsdsBo;
import org.dromara.system.service.IMsdsService;
import org.dromara.common.core.domain.PageResult;

/**
 * Msds
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whml/msds")
public class MsdsController extends BaseController {

    private final IMsdsService msdsService;

    /**
     * 查询Msds列表
     */
    @SaCheckPermission("whml:msds:list")
    @GetMapping("/list")
    public R<PageResult<MsdsVo>> list(MsdsBo bo, PageQuery pageQuery) {
        return R.ok(msdsService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Msds列表
     */
    @SaCheckPermission("whml:msds:export")
    @Log(title = "Msds", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MsdsBo bo, HttpServletResponse response) {
        List<MsdsVo> list = msdsService.queryList(bo);
        ExcelBuilder.of(list, MsdsVo.class).sheetName("Msds").toResponse(response);
    }

    /**
     * 获取Msds详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whml:msds:query")
    @GetMapping("/{id}")
    public R<MsdsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(msdsService.queryById(id));
    }

    /**
     * 新增Msds
     */
    @SaCheckPermission("whml:msds:add")
    @Log(title = "Msds", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MsdsBo bo) {
        return toAjax(msdsService.insertByBo(bo));
    }

    /**
     * 修改Msds
     */
    @SaCheckPermission("whml:msds:edit")
    @Log(title = "Msds", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MsdsBo bo) {
        return toAjax(msdsService.updateByBo(bo));
    }



    /**
     * 删除Msds
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whml:msds:remove")
    @Log(title = "Msds", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(msdsService.deleteWithValidByIds(List.of(ids), true));
    }
}
