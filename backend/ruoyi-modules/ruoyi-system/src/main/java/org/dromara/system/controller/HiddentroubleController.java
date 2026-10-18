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
import org.dromara.system.domain.vo.HiddentroubleVo;
import org.dromara.system.domain.bo.HiddentroubleBo;
import org.dromara.system.service.IHiddentroubleService;
import org.dromara.common.core.domain.PageResult;

/**
 * Hiddentrouble
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/scyh/hiddentrouble")
public class HiddentroubleController extends BaseController {

    private final IHiddentroubleService hiddentroubleService;

    /**
     * 查询Hiddentrouble列表
     */
    @SaCheckPermission("scyh:hiddentrouble:list")
    @GetMapping("/list")
    public R<PageResult<HiddentroubleVo>> list(HiddentroubleBo bo, PageQuery pageQuery) {
        return R.ok(hiddentroubleService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Hiddentrouble列表
     */
    @SaCheckPermission("scyh:hiddentrouble:export")
    @Log(title = "Hiddentrouble", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HiddentroubleBo bo, HttpServletResponse response) {
        List<HiddentroubleVo> list = hiddentroubleService.queryList(bo);
        ExcelBuilder.of(list, HiddentroubleVo.class).sheetName("Hiddentrouble").toResponse(response);
    }

    /**
     * 获取Hiddentrouble详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("scyh:hiddentrouble:query")
    @GetMapping("/{id}")
    public R<HiddentroubleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hiddentroubleService.queryById(id));
    }

    /**
     * 新增Hiddentrouble
     */
    @SaCheckPermission("scyh:hiddentrouble:add")
    @Log(title = "Hiddentrouble", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HiddentroubleBo bo) {
        return toAjax(hiddentroubleService.insertByBo(bo));
    }

    /**
     * 修改Hiddentrouble
     */
    @SaCheckPermission("scyh:hiddentrouble:edit")
    @Log(title = "Hiddentrouble", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HiddentroubleBo bo) {
        return toAjax(hiddentroubleService.updateByBo(bo));
    }



    /**
     * 删除Hiddentrouble
     *
     * @param ids 主键串
     */
    @SaCheckPermission("scyh:hiddentrouble:remove")
    @Log(title = "Hiddentrouble", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hiddentroubleService.deleteWithValidByIds(List.of(ids), true));
    }

    // ==================== 隐患状态流转接口 ====================

    /**
     * 提交隐患（暂存0→提交1）
     */
    @SaCheckPermission("scyh:hiddentrouble:edit")
    @Log(title = "隐患提交", businessType = BusinessType.UPDATE)
    @PutMapping("/submit/{id}")
    public R<Void> submit(@PathVariable Long id) {
        return toAjax(hiddentroubleService.submitHiddentrouble(id));
    }

    /**
     * 整改隐患（提交1→整改2）
     */
    @SaCheckPermission("scyh:hiddentrouble:edit")
    @Log(title = "隐患整改", businessType = BusinessType.UPDATE)
    @PutMapping("/rectify/{id}")
    public R<Void> rectify(@PathVariable Long id) {
        return toAjax(hiddentroubleService.rectifyHiddentrouble(id));
    }

    /**
     * 验证隐患（整改2→验证3）
     */
    @SaCheckPermission("scyh:hiddentrouble:edit")
    @Log(title = "隐患验证", businessType = BusinessType.UPDATE)
    @PutMapping("/verify/{id}")
    public R<Void> verify(@PathVariable Long id) {
        return toAjax(hiddentroubleService.verifyHiddentrouble(id));
    }

    /**
     * 关闭隐患（验证3→关闭4）
     */
    @SaCheckPermission("scyh:hiddentrouble:edit")
    @Log(title = "隐患关闭", businessType = BusinessType.UPDATE)
    @PutMapping("/close/{id}")
    public R<Void> close(@PathVariable Long id) {
        return toAjax(hiddentroubleService.closeHiddentrouble(id));
    }

    /**
     * 撤回隐患（提交1→撤回5）
     */
    @SaCheckPermission("scyh:hiddentrouble:edit")
    @Log(title = "隐患撤回", businessType = BusinessType.UPDATE)
    @PutMapping("/revoke/{id}")
    public R<Void> revoke(@PathVariable Long id) {
        return toAjax(hiddentroubleService.revokeHiddentrouble(id));
    }
}
