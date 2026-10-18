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
import org.dromara.system.domain.vo.CertificateConfigVo;
import org.dromara.system.domain.bo.CertificateConfigBo;
import org.dromara.system.service.ICertificateConfigService;
import org.dromara.common.core.domain.PageResult;

/**
 * CertificateConfig
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pxzs/certificate_config")
public class CertificateConfigController extends BaseController {

    private final ICertificateConfigService certificateConfigService;

    /**
     * 查询CertificateConfig列表
     */
    @SaCheckPermission("pxzs:certificate_config:list")
    @GetMapping("/list")
    public R<PageResult<CertificateConfigVo>> list(CertificateConfigBo bo, PageQuery pageQuery) {
        return R.ok(certificateConfigService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出CertificateConfig列表
     */
    @SaCheckPermission("pxzs:certificate_config:export")
    @Log(title = "CertificateConfig", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CertificateConfigBo bo, HttpServletResponse response) {
        List<CertificateConfigVo> list = certificateConfigService.queryList(bo);
        ExcelBuilder.of(list, CertificateConfigVo.class).sheetName("CertificateConfig").toResponse(response);
    }

    /**
     * 获取CertificateConfig详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pxzs:certificate_config:query")
    @GetMapping("/{id}")
    public R<CertificateConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(certificateConfigService.queryById(id));
    }

    /**
     * 新增CertificateConfig
     */
    @SaCheckPermission("pxzs:certificate_config:add")
    @Log(title = "CertificateConfig", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CertificateConfigBo bo) {
        return toAjax(certificateConfigService.insertByBo(bo));
    }

    /**
     * 修改CertificateConfig
     */
    @SaCheckPermission("pxzs:certificate_config:edit")
    @Log(title = "CertificateConfig", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CertificateConfigBo bo) {
        return toAjax(certificateConfigService.updateByBo(bo));
    }



    /**
     * 删除CertificateConfig
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pxzs:certificate_config:remove")
    @Log(title = "CertificateConfig", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(certificateConfigService.deleteWithValidByIds(List.of(ids), true));
    }
}
