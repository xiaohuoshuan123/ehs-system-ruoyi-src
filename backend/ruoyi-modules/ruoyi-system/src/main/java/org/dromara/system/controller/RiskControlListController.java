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
import org.dromara.system.domain.vo.RiskControlListVo;
import org.dromara.system.domain.bo.RiskControlListBo;
import org.dromara.system.service.IRiskControlListService;
import org.dromara.common.core.domain.PageResult;

/**
 * RiskControlList
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/scfx/risk_control_list")
public class RiskControlListController extends BaseController {

    private final IRiskControlListService riskControlListService;

    /**
     * 查询RiskControlList列表
     */
    @SaCheckPermission("scfx:risk_control_list:list")
    @GetMapping("/list")
    public R<PageResult<RiskControlListVo>> list(RiskControlListBo bo, PageQuery pageQuery) {
        return R.ok(riskControlListService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出RiskControlList列表
     */
    @SaCheckPermission("scfx:risk_control_list:export")
    @Log(title = "RiskControlList", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(RiskControlListBo bo, HttpServletResponse response) {
        List<RiskControlListVo> list = riskControlListService.queryList(bo);
        ExcelBuilder.of(list, RiskControlListVo.class).sheetName("RiskControlList").toResponse(response);
    }

    /**
     * 获取RiskControlList详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("scfx:risk_control_list:query")
    @GetMapping("/{id}")
    public R<RiskControlListVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(riskControlListService.queryById(id));
    }

    /**
     * 新增RiskControlList
     */
    @SaCheckPermission("scfx:risk_control_list:add")
    @Log(title = "RiskControlList", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RiskControlListBo bo) {
        return toAjax(riskControlListService.insertByBo(bo));
    }

    /**
     * 修改RiskControlList
     */
    @SaCheckPermission("scfx:risk_control_list:edit")
    @Log(title = "RiskControlList", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RiskControlListBo bo) {
        return toAjax(riskControlListService.updateByBo(bo));
    }



    /**
     * 删除RiskControlList
     *
     * @param ids 主键串
     */
    @SaCheckPermission("scfx:risk_control_list:remove")
    @Log(title = "RiskControlList", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(riskControlListService.deleteWithValidByIds(List.of(ids), true));
    }
}
