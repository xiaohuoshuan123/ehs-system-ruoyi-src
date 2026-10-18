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
import org.dromara.system.domain.vo.ExamQuestionVo;
import org.dromara.system.domain.bo.ExamQuestionBo;
import org.dromara.system.service.IExamQuestionService;
import org.dromara.common.core.domain.PageResult;

/**
 * ExamQuestion
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxks/exam_question")
public class ExamQuestionController extends BaseController {

    private final IExamQuestionService examQuestionService;

    /**
     * 查询ExamQuestion列表
     */
    @SaCheckPermission("pxks:exam_question:list")
    @GetMapping("/list")
    public R<PageResult<ExamQuestionVo>> list(ExamQuestionBo bo, PageQuery pageQuery) {
        return R.ok(examQuestionService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出ExamQuestion列表
     */
    @SaCheckPermission("pxks:exam_question:export")
    @Log(title = "ExamQuestion", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ExamQuestionBo bo, HttpServletResponse response) {
        List<ExamQuestionVo> list = examQuestionService.queryList(bo);
        ExcelBuilder.of(list, ExamQuestionVo.class).sheetName("ExamQuestion").toResponse(response);
    }

    /**
     * 获取ExamQuestion详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxks:exam_question:query")
    @GetMapping("/{id}")
    public R<ExamQuestionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(examQuestionService.queryById(id));
    }

    /**
     * 新增ExamQuestion
     */
    @SaCheckPermission("pxks:exam_question:add")
    @Log(title = "ExamQuestion", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ExamQuestionBo bo) {
        return toAjax(examQuestionService.insertByBo(bo));
    }

    /**
     * 修改ExamQuestion
     */
    @SaCheckPermission("pxks:exam_question:edit")
    @Log(title = "ExamQuestion", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ExamQuestionBo bo) {
        return toAjax(examQuestionService.updateByBo(bo));
    }



    /**
     * 删除ExamQuestion
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxks:exam_question:remove")
    @Log(title = "ExamQuestion", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(examQuestionService.deleteWithValidByIds(List.of(ids), true));
    }
}
