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
import org.dromara.system.domain.vo.StorageVo;
import org.dromara.system.domain.bo.StorageBo;
import org.dromara.system.service.IStorageService;
import org.dromara.common.core.domain.PageResult;

/**
 * Storage
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whtz/storage")
public class StorageController extends BaseController {

    private final IStorageService storageService;

    /**
     * 查询Storage列表
     */
    @SaCheckPermission("whtz:storage:list")
    @GetMapping("/list")
    public R<PageResult<StorageVo>> list(StorageBo bo, PageQuery pageQuery) {
        return R.ok(storageService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出Storage列表
     */
    @SaCheckPermission("whtz:storage:export")
    @Log(title = "Storage", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StorageBo bo, HttpServletResponse response) {
        List<StorageVo> list = storageService.queryList(bo);
        ExcelBuilder.of(list, StorageVo.class).sheetName("Storage").toResponse(response);
    }

    /**
     * 获取Storage详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whtz:storage:query")
    @GetMapping("/{id}")
    public R<StorageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(storageService.queryById(id));
    }

    /**
     * 新增Storage
     */
    @SaCheckPermission("whtz:storage:add")
    @Log(title = "Storage", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StorageBo bo) {
        return toAjax(storageService.insertByBo(bo));
    }

    /**
     * 修改Storage
     */
    @SaCheckPermission("whtz:storage:edit")
    @Log(title = "Storage", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StorageBo bo) {
        return toAjax(storageService.updateByBo(bo));
    }



    /**
     * 删除Storage
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whtz:storage:remove")
    @Log(title = "Storage", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(storageService.deleteWithValidByIds(List.of(ids), true));
    }
}
