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
import org.dromara.system.domain.vo.AccidentVo;
import org.dromara.system.domain.bo.AccidentBo;
import org.dromara.system.service.IAccidentService;
import org.dromara.common.core.domain.PageResult;

/**
 * Accident
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/scfx/accident")
public class AccidentController extends BaseController {

    private final IAccidentService accidentService;

    /**
     * 查询Accident列表
     */
    @SaCheckPermission("scfx:accident:list")
    @GetMapping("/list")
    public R<PageResult<AccidentVo>> list(AccidentBo bo, PageQuery pageQuery) {
        return R.ok(accidentService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Accident列表
     */
    @SaCheckPermission("scfx:accident:export")
    @Log(title = "Accident", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AccidentBo bo, HttpServletResponse response) {
        List<AccidentVo> list = accidentService.queryList(bo);
        ExcelBuilder.of(list, AccidentVo.class).sheetName("Accident").toResponse(response);
    }

    /**
     * 获取Accident详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("scfx:accident:query")
    @GetMapping("/{id}")
    public R<AccidentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(accidentService.queryById(id));
    }

    /**
     * 新增Accident
     */
    @SaCheckPermission("scfx:accident:add")
    @Log(title = "Accident", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AccidentBo bo) {
        return toAjax(accidentService.insertByBo(bo));
    }

    /**
     * 修改Accident
     */
    @SaCheckPermission("scfx:accident:edit")
    @Log(title = "Accident", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AccidentBo bo) {
        return toAjax(accidentService.updateByBo(bo));
    }



    /**
     * 删除Accident
     *
     * @param ids 主键串
     */
    @SaCheckPermission("scfx:accident:remove")
    @Log(title = "Accident", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(accidentService.deleteWithValidByIds(List.of(ids), true));
    }
}
