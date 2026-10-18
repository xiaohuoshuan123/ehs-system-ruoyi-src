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
import org.dromara.system.domain.vo.SignDispatchVo;
import org.dromara.system.domain.bo.SignDispatchBo;
import org.dromara.system.service.ISignDispatchService;
import org.dromara.common.core.domain.PageResult;

/**
 * SignDispatch
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/mbgr/sign_dispatch")
public class SignDispatchController extends BaseController {

    private final ISignDispatchService signDispatchService;

    /**
     * 查询SignDispatch列表
     */
    @SaCheckPermission("mbgr:sign_dispatch:list")
    @GetMapping("/list")
    public R<PageResult<SignDispatchVo>> list(SignDispatchBo bo, PageQuery pageQuery) {
        return R.ok(signDispatchService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出SignDispatch列表
     */
    @SaCheckPermission("mbgr:sign_dispatch:export")
    @Log(title = "SignDispatch", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SignDispatchBo bo, HttpServletResponse response) {
        List<SignDispatchVo> list = signDispatchService.queryList(bo);
        ExcelBuilder.of(list, SignDispatchVo.class).sheetName("SignDispatch").toResponse(response);
    }

    /**
     * 获取SignDispatch详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("mbgr:sign_dispatch:query")
    @GetMapping("/{id}")
    public R<SignDispatchVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(signDispatchService.queryById(id));
    }

    /**
     * 新增SignDispatch
     */
    @SaCheckPermission("mbgr:sign_dispatch:add")
    @Log(title = "SignDispatch", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SignDispatchBo bo) {
        return toAjax(signDispatchService.insertByBo(bo));
    }

    /**
     * 修改SignDispatch
     */
    @SaCheckPermission("mbgr:sign_dispatch:edit")
    @Log(title = "SignDispatch", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SignDispatchBo bo) {
        return toAjax(signDispatchService.updateByBo(bo));
    }



    /**
     * 删除SignDispatch
     *
     * @param ids 主键串
     */
    @SaCheckPermission("mbgr:sign_dispatch:remove")
    @Log(title = "SignDispatch", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(signDispatchService.deleteWithValidByIds(List.of(ids), true));
    }
}
