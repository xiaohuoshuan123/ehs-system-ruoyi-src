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
import org.dromara.system.domain.vo.SignTemplateVo;
import org.dromara.system.domain.bo.SignTemplateBo;
import org.dromara.system.service.ISignTemplateService;
import org.dromara.common.core.domain.PageResult;

/**
 * SignTemplate
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/mbgr/sign_template")
public class SignTemplateController extends BaseController {

    private final ISignTemplateService signTemplateService;

    /**
     * 查询SignTemplate列表
     */
    @SaCheckPermission("mbgr:sign_template:list")
    @GetMapping("/list")
    public R<PageResult<SignTemplateVo>> list(SignTemplateBo bo, PageQuery pageQuery) {
        return R.ok(signTemplateService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SignTemplate列表
     */
    @SaCheckPermission("mbgr:sign_template:export")
    @Log(title = "SignTemplate", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SignTemplateBo bo, HttpServletResponse response) {
        List<SignTemplateVo> list = signTemplateService.queryList(bo);
        ExcelBuilder.of(list, SignTemplateVo.class).sheetName("SignTemplate").toResponse(response);
    }

    /**
     * 获取SignTemplate详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("mbgr:sign_template:query")
    @GetMapping("/{id}")
    public R<SignTemplateVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(signTemplateService.queryById(id));
    }

    /**
     * 新增SignTemplate
     */
    @SaCheckPermission("mbgr:sign_template:add")
    @Log(title = "SignTemplate", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SignTemplateBo bo) {
        return toAjax(signTemplateService.insertByBo(bo));
    }

    /**
     * 修改SignTemplate
     */
    @SaCheckPermission("mbgr:sign_template:edit")
    @Log(title = "SignTemplate", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SignTemplateBo bo) {
        return toAjax(signTemplateService.updateByBo(bo));
    }



    /**
     * 删除SignTemplate
     *
     * @param ids 主键串
     */
    @SaCheckPermission("mbgr:sign_template:remove")
    @Log(title = "SignTemplate", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(signTemplateService.deleteWithValidByIds(List.of(ids), true));
    }
}
