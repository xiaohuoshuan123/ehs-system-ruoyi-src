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
import org.dromara.system.domain.vo.PositionConfigureVo;
import org.dromara.system.domain.bo.PositionConfigureBo;
import org.dromara.system.service.IPositionConfigureService;
import org.dromara.common.core.domain.PageResult;

/**
 * PositionConfigure
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yfxc/position_configure")
public class PositionConfigureController extends BaseController {

    private final IPositionConfigureService positionConfigureService;

    /**
     * 查询PositionConfigure列表
     */
    @SaCheckPermission("yfxc:position_configure:list")
    @GetMapping("/list")
    public R<PageResult<PositionConfigureVo>> list(PositionConfigureBo bo, PageQuery pageQuery) {
        return R.ok(positionConfigureService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出PositionConfigure列表
     */
    @SaCheckPermission("yfxc:position_configure:export")
    @Log(title = "PositionConfigure", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PositionConfigureBo bo, HttpServletResponse response) {
        List<PositionConfigureVo> list = positionConfigureService.queryList(bo);
        ExcelBuilder.of(list, PositionConfigureVo.class).sheetName("PositionConfigure").toResponse(response);
    }

    /**
     * 获取PositionConfigure详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yfxc:position_configure:query")
    @GetMapping("/{id}")
    public R<PositionConfigureVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(positionConfigureService.queryById(id));
    }

    /**
     * 新增PositionConfigure
     */
    @SaCheckPermission("yfxc:position_configure:add")
    @Log(title = "PositionConfigure", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PositionConfigureBo bo) {
        return toAjax(positionConfigureService.insertByBo(bo));
    }

    /**
     * 修改PositionConfigure
     */
    @SaCheckPermission("yfxc:position_configure:edit")
    @Log(title = "PositionConfigure", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PositionConfigureBo bo) {
        return toAjax(positionConfigureService.updateByBo(bo));
    }



    /**
     * 删除PositionConfigure
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yfxc:position_configure:remove")
    @Log(title = "PositionConfigure", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(positionConfigureService.deleteWithValidByIds(List.of(ids), true));
    }
}
