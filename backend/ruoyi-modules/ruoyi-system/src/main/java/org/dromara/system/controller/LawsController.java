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
import org.dromara.system.domain.vo.LawsVo;
import org.dromara.system.domain.bo.LawsBo;
import org.dromara.system.service.ILawsService;
import org.dromara.common.core.domain.PageResult;

/**
 * Laws
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/zbfl/laws")
public class LawsController extends BaseController {

    private final ILawsService lawsService;

    /**
     * 查询Laws列表
     */
    @SaCheckPermission("zbfl:laws:list")
    @GetMapping("/list")
    public R<PageResult<LawsVo>> list(LawsBo bo, PageQuery pageQuery) {
        return R.ok(lawsService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Laws列表
     */
    @SaCheckPermission("zbfl:laws:export")
    @Log(title = "Laws", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(LawsBo bo, HttpServletResponse response) {
        List<LawsVo> list = lawsService.queryList(bo);
        ExcelBuilder.of(list, LawsVo.class).sheetName("Laws").toResponse(response);
    }

    /**
     * 获取Laws详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zbfl:laws:query")
    @GetMapping("/{id}")
    public R<LawsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(lawsService.queryById(id));
    }

    /**
     * 新增Laws
     */
    @SaCheckPermission("zbfl:laws:add")
    @Log(title = "Laws", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody LawsBo bo) {
        return toAjax(lawsService.insertByBo(bo));
    }

    /**
     * 修改Laws
     */
    @SaCheckPermission("zbfl:laws:edit")
    @Log(title = "Laws", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody LawsBo bo) {
        return toAjax(lawsService.updateByBo(bo));
    }



    /**
     * 删除Laws
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zbfl:laws:remove")
    @Log(title = "Laws", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(lawsService.deleteWithValidByIds(List.of(ids), true));
    }
}
