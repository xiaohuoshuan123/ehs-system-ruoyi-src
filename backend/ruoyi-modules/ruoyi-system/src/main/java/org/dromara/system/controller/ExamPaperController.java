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
import org.dromara.system.domain.vo.ExamPaperVo;
import org.dromara.system.domain.bo.ExamPaperBo;
import org.dromara.system.service.IExamPaperService;
import org.dromara.common.core.domain.PageResult;

/**
 * ExamPaper
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxks/exam_paper")
public class ExamPaperController extends BaseController {

    private final IExamPaperService examPaperService;

    /**
     * 查询ExamPaper列表
     */
    @SaCheckPermission("pxks:exam_paper:list")
    @GetMapping("/list")
    public R<PageResult<ExamPaperVo>> list(ExamPaperBo bo, PageQuery pageQuery) {
        return R.ok(examPaperService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出ExamPaper列表
     */
    @SaCheckPermission("pxks:exam_paper:export")
    @Log(title = "ExamPaper", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ExamPaperBo bo, HttpServletResponse response) {
        List<ExamPaperVo> list = examPaperService.queryList(bo);
        ExcelBuilder.of(list, ExamPaperVo.class).sheetName("ExamPaper").toResponse(response);
    }

    /**
     * 获取ExamPaper详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxks:exam_paper:query")
    @GetMapping("/{id}")
    public R<ExamPaperVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(examPaperService.queryById(id));
    }

    /**
     * 新增ExamPaper
     */
    @SaCheckPermission("pxks:exam_paper:add")
    @Log(title = "ExamPaper", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ExamPaperBo bo) {
        return toAjax(examPaperService.insertByBo(bo));
    }

    /**
     * 修改ExamPaper
     */
    @SaCheckPermission("pxks:exam_paper:edit")
    @Log(title = "ExamPaper", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ExamPaperBo bo) {
        return toAjax(examPaperService.updateByBo(bo));
    }



    /**
     * 删除ExamPaper
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxks:exam_paper:remove")
    @Log(title = "ExamPaper", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(examPaperService.deleteWithValidByIds(List.of(ids), true));
    }
}
