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
import org.dromara.system.domain.vo.MasterApprenticeVo;
import org.dromara.system.domain.bo.MasterApprenticeBo;
import org.dromara.system.service.IMasterApprenticeService;
import org.dromara.common.core.domain.PageResult;

/**
 * MasterApprentice
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/mp/master_apprentice")
public class MasterApprenticeController extends BaseController {

    private final IMasterApprenticeService masterApprenticeService;

    /**
     * 查询MasterApprentice列表
     */
    @SaCheckPermission("mp:master_apprentice:list")
    @GetMapping("/list")
    public R<PageResult<MasterApprenticeVo>> list(MasterApprenticeBo bo, PageQuery pageQuery) {
        return R.ok(masterApprenticeService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出MasterApprentice列表
     */
    @SaCheckPermission("mp:master_apprentice:export")
    @Log(title = "MasterApprentice", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MasterApprenticeBo bo, HttpServletResponse response) {
        List<MasterApprenticeVo> list = masterApprenticeService.queryList(bo);
        ExcelBuilder.of(list, MasterApprenticeVo.class).sheetName("MasterApprentice").toResponse(response);
    }

    /**
     * 获取MasterApprentice详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("mp:master_apprentice:query")
    @GetMapping("/{id}")
    public R<MasterApprenticeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(masterApprenticeService.queryById(id));
    }

    /**
     * 新增MasterApprentice
     */
    @SaCheckPermission("mp:master_apprentice:add")
    @Log(title = "MasterApprentice", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MasterApprenticeBo bo) {
        return toAjax(masterApprenticeService.insertByBo(bo));
    }

    /**
     * 修改MasterApprentice
     */
    @SaCheckPermission("mp:master_apprentice:edit")
    @Log(title = "MasterApprentice", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MasterApprenticeBo bo) {
        return toAjax(masterApprenticeService.updateByBo(bo));
    }



    /**
     * 删除MasterApprentice
     *
     * @param ids 主键串
     */
    @SaCheckPermission("mp:master_apprentice:remove")
    @Log(title = "MasterApprentice", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(masterApprenticeService.deleteWithValidByIds(List.of(ids), true));
    }
}
