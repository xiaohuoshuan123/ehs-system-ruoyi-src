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
import org.dromara.system.domain.vo.EducationTrainingRecordVo;
import org.dromara.system.domain.bo.EducationTrainingRecordBo;
import org.dromara.system.service.IEducationTrainingRecordService;
import org.dromara.common.core.domain.PageResult;

/**
 * EducationTrainingRecord
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxjl/education_training_record")
public class EducationTrainingRecordController extends BaseController {

    private final IEducationTrainingRecordService educationTrainingRecordService;

    /**
     * 查询EducationTrainingRecord列表
     */
    @SaCheckPermission("pxjl:education_training_record:list")
    @GetMapping("/list")
    public R<PageResult<EducationTrainingRecordVo>> list(EducationTrainingRecordBo bo, PageQuery pageQuery) {
        return R.ok(educationTrainingRecordService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EducationTrainingRecord列表
     */
    @SaCheckPermission("pxjl:education_training_record:export")
    @Log(title = "EducationTrainingRecord", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EducationTrainingRecordBo bo, HttpServletResponse response) {
        List<EducationTrainingRecordVo> list = educationTrainingRecordService.queryList(bo);
        ExcelBuilder.of(list, EducationTrainingRecordVo.class).sheetName("EducationTrainingRecord").toResponse(response);
    }

    /**
     * 获取EducationTrainingRecord详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxjl:education_training_record:query")
    @GetMapping("/{id}")
    public R<EducationTrainingRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(educationTrainingRecordService.queryById(id));
    }

    /**
     * 新增EducationTrainingRecord
     */
    @SaCheckPermission("pxjl:education_training_record:add")
    @Log(title = "EducationTrainingRecord", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EducationTrainingRecordBo bo) {
        return toAjax(educationTrainingRecordService.insertByBo(bo));
    }

    /**
     * 修改EducationTrainingRecord
     */
    @SaCheckPermission("pxjl:education_training_record:edit")
    @Log(title = "EducationTrainingRecord", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EducationTrainingRecordBo bo) {
        return toAjax(educationTrainingRecordService.updateByBo(bo));
    }



    /**
     * 删除EducationTrainingRecord
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxjl:education_training_record:remove")
    @Log(title = "EducationTrainingRecord", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(educationTrainingRecordService.deleteWithValidByIds(List.of(ids), true));
    }
}
