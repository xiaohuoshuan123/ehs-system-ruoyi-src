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
import org.dromara.system.domain.vo.LawModelVo;
import org.dromara.system.domain.bo.LawModelBo;
import org.dromara.system.service.ILawModelService;
import org.dromara.common.core.domain.PageResult;

/**
 * LawModel
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lzbz/law_model")
public class LawModelController extends BaseController {

    private final ILawModelService lawModelService;

    /**
     * 查询LawModel列表
     */
    @SaCheckPermission("lzbz:law_model:list")
    @GetMapping("/list")
    public R<PageResult<LawModelVo>> list(LawModelBo bo, PageQuery pageQuery) {
        return R.ok(lawModelService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出LawModel列表
     */
    @SaCheckPermission("lzbz:law_model:export")
    @Log(title = "LawModel", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(LawModelBo bo, HttpServletResponse response) {
        List<LawModelVo> list = lawModelService.queryList(bo);
        ExcelBuilder.of(list, LawModelVo.class).sheetName("LawModel").toResponse(response);
    }

    /**
     * 获取LawModel详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lzbz:law_model:query")
    @GetMapping("/{id}")
    public R<LawModelVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(lawModelService.queryById(id));
    }

    /**
     * 新增LawModel
     */
    @SaCheckPermission("lzbz:law_model:add")
    @Log(title = "LawModel", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody LawModelBo bo) {
        return toAjax(lawModelService.insertByBo(bo));
    }

    /**
     * 修改LawModel
     */
    @SaCheckPermission("lzbz:law_model:edit")
    @Log(title = "LawModel", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody LawModelBo bo) {
        return toAjax(lawModelService.updateByBo(bo));
    }



    /**
     * 删除LawModel
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lzbz:law_model:remove")
    @Log(title = "LawModel", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(lawModelService.deleteWithValidByIds(List.of(ids), true));
    }
}
