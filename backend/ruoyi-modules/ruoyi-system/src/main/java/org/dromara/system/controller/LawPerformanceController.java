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
import org.dromara.system.domain.vo.LawPerformanceVo;
import org.dromara.system.domain.bo.LawPerformanceBo;
import org.dromara.system.service.ILawPerformanceService;
import org.dromara.common.core.domain.PageResult;

/**
 * LawPerformance
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lzsj/law_performance")
public class LawPerformanceController extends BaseController {

    private final ILawPerformanceService lawPerformanceService;

    /**
     * 查询LawPerformance列表
     */
    @SaCheckPermission("lzsj:law_performance:list")
    @GetMapping("/list")
    public R<PageResult<LawPerformanceVo>> list(LawPerformanceBo bo, PageQuery pageQuery) {
        return R.ok(lawPerformanceService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出LawPerformance列表
     */
    @SaCheckPermission("lzsj:law_performance:export")
    @Log(title = "LawPerformance", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(LawPerformanceBo bo, HttpServletResponse response) {
        List<LawPerformanceVo> list = lawPerformanceService.queryList(bo);
        ExcelBuilder.of(list, LawPerformanceVo.class).sheetName("LawPerformance").toResponse(response);
    }

    /**
     * 获取LawPerformance详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lzsj:law_performance:query")
    @GetMapping("/{id}")
    public R<LawPerformanceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(lawPerformanceService.queryById(id));
    }

    /**
     * 新增LawPerformance
     */
    @SaCheckPermission("lzsj:law_performance:add")
    @Log(title = "LawPerformance", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody LawPerformanceBo bo) {
        return toAjax(lawPerformanceService.insertByBo(bo));
    }

    /**
     * 修改LawPerformance
     */
    @SaCheckPermission("lzsj:law_performance:edit")
    @Log(title = "LawPerformance", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody LawPerformanceBo bo) {
        return toAjax(lawPerformanceService.updateByBo(bo));
    }



    /**
     * 删除LawPerformance
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lzsj:law_performance:remove")
    @Log(title = "LawPerformance", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(lawPerformanceService.deleteWithValidByIds(List.of(ids), true));
    }
}
