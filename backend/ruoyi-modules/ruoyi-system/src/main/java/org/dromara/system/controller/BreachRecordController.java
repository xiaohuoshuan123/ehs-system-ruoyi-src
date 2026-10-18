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
import org.dromara.system.domain.vo.BreachRecordVo;
import org.dromara.system.domain.bo.BreachRecordBo;
import org.dromara.system.service.IBreachRecordService;
import org.dromara.common.core.domain.PageResult;

/**
 * BreachRecord
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wzjl/breach_record")
public class BreachRecordController extends BaseController {

    private final IBreachRecordService breachRecordService;

    /**
     * 查询BreachRecord列表
     */
    @SaCheckPermission("wzjl:breach_record:list")
    @GetMapping("/list")
    public R<PageResult<BreachRecordVo>> list(BreachRecordBo bo, PageQuery pageQuery) {
        return R.ok(breachRecordService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出BreachRecord列表
     */
    @SaCheckPermission("wzjl:breach_record:export")
    @Log(title = "BreachRecord", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BreachRecordBo bo, HttpServletResponse response) {
        List<BreachRecordVo> list = breachRecordService.queryList(bo);
        ExcelBuilder.of(list, BreachRecordVo.class).sheetName("BreachRecord").toResponse(response);
    }

    /**
     * 获取BreachRecord详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wzjl:breach_record:query")
    @GetMapping("/{id}")
    public R<BreachRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(breachRecordService.queryById(id));
    }

    /**
     * 新增BreachRecord
     */
    @SaCheckPermission("wzjl:breach_record:add")
    @Log(title = "BreachRecord", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BreachRecordBo bo) {
        return toAjax(breachRecordService.insertByBo(bo));
    }

    /**
     * 修改BreachRecord
     */
    @SaCheckPermission("wzjl:breach_record:edit")
    @Log(title = "BreachRecord", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BreachRecordBo bo) {
        return toAjax(breachRecordService.updateByBo(bo));
    }



    /**
     * 删除BreachRecord
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wzjl:breach_record:remove")
    @Log(title = "BreachRecord", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(breachRecordService.deleteWithValidByIds(List.of(ids), true));
    }
}
