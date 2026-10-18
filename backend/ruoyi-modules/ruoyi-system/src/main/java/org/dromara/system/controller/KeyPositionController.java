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
import org.dromara.system.domain.vo.KeyPositionVo;
import org.dromara.system.domain.bo.KeyPositionBo;
import org.dromara.system.service.IKeyPositionService;
import org.dromara.common.core.domain.PageResult;

/**
 * KeyPosition
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yfbw/key_position")
public class KeyPositionController extends BaseController {

    private final IKeyPositionService keyPositionService;

    /**
     * 查询KeyPosition列表
     */
    @SaCheckPermission("yfbw:key_position:list")
    @GetMapping("/list")
    public R<PageResult<KeyPositionVo>> list(KeyPositionBo bo, PageQuery pageQuery) {
        return R.ok(keyPositionService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出KeyPosition列表
     */
    @SaCheckPermission("yfbw:key_position:export")
    @Log(title = "KeyPosition", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(KeyPositionBo bo, HttpServletResponse response) {
        List<KeyPositionVo> list = keyPositionService.queryList(bo);
        ExcelBuilder.of(list, KeyPositionVo.class).sheetName("KeyPosition").toResponse(response);
    }

    /**
     * 获取KeyPosition详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("yfbw:key_position:query")
    @GetMapping("/{id}")
    public R<KeyPositionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(keyPositionService.queryById(id));
    }

    /**
     * 新增KeyPosition
     */
    @SaCheckPermission("yfbw:key_position:add")
    @Log(title = "KeyPosition", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody KeyPositionBo bo) {
        return toAjax(keyPositionService.insertByBo(bo));
    }

    /**
     * 修改KeyPosition
     */
    @SaCheckPermission("yfbw:key_position:edit")
    @Log(title = "KeyPosition", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody KeyPositionBo bo) {
        return toAjax(keyPositionService.updateByBo(bo));
    }



    /**
     * 删除KeyPosition
     *
     * @param ids 主键串
     */
    @SaCheckPermission("yfbw:key_position:remove")
    @Log(title = "KeyPosition", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(keyPositionService.deleteWithValidByIds(List.of(ids), true));
    }
}
