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
import org.dromara.system.domain.vo.ArchitectureVo;
import org.dromara.system.domain.bo.ArchitectureBo;
import org.dromara.system.service.IArchitectureService;
import org.dromara.common.core.domain.PageResult;

/**
 * Architecture
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jgaw/architecture")
public class ArchitectureController extends BaseController {

    private final IArchitectureService architectureService;

    /**
     * 查询Architecture列表
     */
    @SaCheckPermission("jgaw:architecture:list")
    @GetMapping("/list")
    public R<PageResult<ArchitectureVo>> list(ArchitectureBo bo, PageQuery pageQuery) {
        return R.ok(architectureService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Architecture列表
     */
    @SaCheckPermission("jgaw:architecture:export")
    @Log(title = "Architecture", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ArchitectureBo bo, HttpServletResponse response) {
        List<ArchitectureVo> list = architectureService.queryList(bo);
        ExcelBuilder.of(list, ArchitectureVo.class).sheetName("Architecture").toResponse(response);
    }

    /**
     * 获取Architecture详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jgaw:architecture:query")
    @GetMapping("/{id}")
    public R<ArchitectureVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(architectureService.queryById(id));
    }

    /**
     * 新增Architecture
     */
    @SaCheckPermission("jgaw:architecture:add")
    @Log(title = "Architecture", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ArchitectureBo bo) {
        return toAjax(architectureService.insertByBo(bo));
    }

    /**
     * 修改Architecture
     */
    @SaCheckPermission("jgaw:architecture:edit")
    @Log(title = "Architecture", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ArchitectureBo bo) {
        return toAjax(architectureService.updateByBo(bo));
    }



    /**
     * 删除Architecture
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jgaw:architecture:remove")
    @Log(title = "Architecture", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(architectureService.deleteWithValidByIds(List.of(ids), true));
    }
}
