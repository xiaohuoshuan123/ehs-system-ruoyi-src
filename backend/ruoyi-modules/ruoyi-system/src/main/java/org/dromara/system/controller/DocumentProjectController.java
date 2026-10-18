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
import org.dromara.system.domain.vo.DocumentProjectVo;
import org.dromara.system.domain.bo.DocumentProjectBo;
import org.dromara.system.service.IDocumentProjectService;
import org.dromara.common.core.domain.PageResult;

/**
 * DocumentProject
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wd/document_project")
public class DocumentProjectController extends BaseController {

    private final IDocumentProjectService documentProjectService;

    /**
     * 查询DocumentProject列表
     */
    @SaCheckPermission("wd:document_project:list")
    @GetMapping("/list")
    public R<PageResult<DocumentProjectVo>> list(DocumentProjectBo bo, PageQuery pageQuery) {
        return R.ok(documentProjectService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出DocumentProject列表
     */
    @SaCheckPermission("wd:document_project:export")
    @Log(title = "DocumentProject", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DocumentProjectBo bo, HttpServletResponse response) {
        List<DocumentProjectVo> list = documentProjectService.queryList(bo);
        ExcelBuilder.of(list, DocumentProjectVo.class).sheetName("DocumentProject").toResponse(response);
    }

    /**
     * 获取DocumentProject详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wd:document_project:query")
    @GetMapping("/{id}")
    public R<DocumentProjectVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(documentProjectService.queryById(id));
    }

    /**
     * 新增DocumentProject
     */
    @SaCheckPermission("wd:document_project:add")
    @Log(title = "DocumentProject", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DocumentProjectBo bo) {
        return toAjax(documentProjectService.insertByBo(bo));
    }

    /**
     * 修改DocumentProject
     */
    @SaCheckPermission("wd:document_project:edit")
    @Log(title = "DocumentProject", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DocumentProjectBo bo) {
        return toAjax(documentProjectService.updateByBo(bo));
    }



    /**
     * 删除DocumentProject
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wd:document_project:remove")
    @Log(title = "DocumentProject", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(documentProjectService.deleteWithValidByIds(List.of(ids), true));
    }
}
