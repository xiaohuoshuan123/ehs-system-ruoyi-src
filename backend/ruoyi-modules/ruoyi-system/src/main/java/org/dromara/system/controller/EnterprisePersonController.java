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
import org.dromara.system.domain.vo.EnterprisePersonVo;
import org.dromara.system.domain.bo.EnterprisePersonBo;
import org.dromara.system.service.IEnterprisePersonService;
import org.dromara.common.core.domain.PageResult;

/**
 * EnterprisePerson
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxzs/enterprise_person")
public class EnterprisePersonController extends BaseController {

    private final IEnterprisePersonService enterprisePersonService;

    /**
     * 查询EnterprisePerson列表
     */
    @SaCheckPermission("pxzs:enterprise_person:list")
    @GetMapping("/list")
    public R<PageResult<EnterprisePersonVo>> list(EnterprisePersonBo bo, PageQuery pageQuery) {
        return R.ok(enterprisePersonService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出EnterprisePerson列表
     */
    @SaCheckPermission("pxzs:enterprise_person:export")
    @Log(title = "EnterprisePerson", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EnterprisePersonBo bo, HttpServletResponse response) {
        List<EnterprisePersonVo> list = enterprisePersonService.queryList(bo);
        ExcelBuilder.of(list, EnterprisePersonVo.class).sheetName("EnterprisePerson").toResponse(response);
    }

    /**
     * 获取EnterprisePerson详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxzs:enterprise_person:query")
    @GetMapping("/{id}")
    public R<EnterprisePersonVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(enterprisePersonService.queryById(id));
    }

    /**
     * 新增EnterprisePerson
     */
    @SaCheckPermission("pxzs:enterprise_person:add")
    @Log(title = "EnterprisePerson", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EnterprisePersonBo bo) {
        return toAjax(enterprisePersonService.insertByBo(bo));
    }

    /**
     * 修改EnterprisePerson
     */
    @SaCheckPermission("pxzs:enterprise_person:edit")
    @Log(title = "EnterprisePerson", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EnterprisePersonBo bo) {
        return toAjax(enterprisePersonService.updateByBo(bo));
    }



    /**
     * 删除EnterprisePerson
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxzs:enterprise_person:remove")
    @Log(title = "EnterprisePerson", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(enterprisePersonService.deleteWithValidByIds(List.of(ids), true));
    }
}
