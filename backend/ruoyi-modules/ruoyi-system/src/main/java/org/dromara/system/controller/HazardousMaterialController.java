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
import org.dromara.system.domain.vo.HazardousMaterialVo;
import org.dromara.system.domain.bo.HazardousMaterialBo;
import org.dromara.system.service.IHazardousMaterialService;
import org.dromara.common.core.domain.PageResult;

/**
 * HazardousMaterial
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whml/hazardous_material")
public class HazardousMaterialController extends BaseController {

    private final IHazardousMaterialService hazardousMaterialService;

    /**
     * 查询HazardousMaterial列表
     */
    @SaCheckPermission("whml:hazardous_material:list")
    @GetMapping("/list")
    public R<PageResult<HazardousMaterialVo>> list(HazardousMaterialBo bo, PageQuery pageQuery) {
        return R.ok(hazardousMaterialService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出HazardousMaterial列表
     */
    @SaCheckPermission("whml:hazardous_material:export")
    @Log(title = "HazardousMaterial", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HazardousMaterialBo bo, HttpServletResponse response) {
        List<HazardousMaterialVo> list = hazardousMaterialService.queryList(bo);
        ExcelBuilder.of(list, HazardousMaterialVo.class).sheetName("HazardousMaterial").toResponse(response);
    }

    /**
     * 获取HazardousMaterial详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whml:hazardous_material:query")
    @GetMapping("/{id}")
    public R<HazardousMaterialVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hazardousMaterialService.queryById(id));
    }

    /**
     * 新增HazardousMaterial
     */
    @SaCheckPermission("whml:hazardous_material:add")
    @Log(title = "HazardousMaterial", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HazardousMaterialBo bo) {
        return toAjax(hazardousMaterialService.insertByBo(bo));
    }

    /**
     * 修改HazardousMaterial
     */
    @SaCheckPermission("whml:hazardous_material:edit")
    @Log(title = "HazardousMaterial", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HazardousMaterialBo bo) {
        return toAjax(hazardousMaterialService.updateByBo(bo));
    }



    /**
     * 删除HazardousMaterial
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whml:hazardous_material:remove")
    @Log(title = "HazardousMaterial", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hazardousMaterialService.deleteWithValidByIds(List.of(ids), true));
    }
}
