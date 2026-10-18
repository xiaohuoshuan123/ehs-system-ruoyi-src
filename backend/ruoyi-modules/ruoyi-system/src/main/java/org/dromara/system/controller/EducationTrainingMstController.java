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
import org.dromara.system.domain.vo.EducationTrainingMstVo;
import org.dromara.system.domain.bo.EducationTrainingMstBo;
import org.dromara.system.service.IEducationTrainingMstService;
import org.dromara.common.core.domain.PageResult;

/**
 * EducationTrainingMst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxjl/education_training_mst")
public class EducationTrainingMstController extends BaseController {

    private final IEducationTrainingMstService educationTrainingMstService;

    /**
     * 查询EducationTrainingMst列表
     */
    @SaCheckPermission("pxjl:education_training_mst:list")
    @GetMapping("/list")
    public R<PageResult<EducationTrainingMstVo>> list(EducationTrainingMstBo bo, PageQuery pageQuery) {
        return R.ok(educationTrainingMstService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EducationTrainingMst列表
     */
    @SaCheckPermission("pxjl:education_training_mst:export")
    @Log(title = "EducationTrainingMst", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EducationTrainingMstBo bo, HttpServletResponse response) {
        List<EducationTrainingMstVo> list = educationTrainingMstService.queryList(bo);
        ExcelBuilder.of(list, EducationTrainingMstVo.class).sheetName("EducationTrainingMst").toResponse(response);
    }

    /**
     * 获取EducationTrainingMst详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxjl:education_training_mst:query")
    @GetMapping("/{id}")
    public R<EducationTrainingMstVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(educationTrainingMstService.queryById(id));
    }

    /**
     * 新增EducationTrainingMst
     */
    @SaCheckPermission("pxjl:education_training_mst:add")
    @Log(title = "EducationTrainingMst", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EducationTrainingMstBo bo) {
        return toAjax(educationTrainingMstService.insertByBo(bo));
    }

    /**
     * 修改EducationTrainingMst
     */
    @SaCheckPermission("pxjl:education_training_mst:edit")
    @Log(title = "EducationTrainingMst", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EducationTrainingMstBo bo) {
        return toAjax(educationTrainingMstService.updateByBo(bo));
    }



    /**
     * 删除EducationTrainingMst
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxjl:education_training_mst:remove")
    @Log(title = "EducationTrainingMst", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(educationTrainingMstService.deleteWithValidByIds(List.of(ids), true));
    }
}
