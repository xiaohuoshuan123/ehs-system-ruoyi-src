package org.dromara.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.query.QueryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.StorageBo;
import org.dromara.system.domain.vo.StorageVo;
import org.dromara.system.domain.Storage;
import org.dromara.system.mapper.StorageMapper;
import org.dromara.system.service.IStorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * StorageService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements IStorageService {

    private final StorageMapper storageMapper;

    /**
     * 查询Storage
     *
     * @param id 主键
     * @return Storage
     */
    @Override
    public StorageVo queryById(Long id) {
        return storageMapper.selectVoById(id);
    }

    /**
     * 分页查询Storage列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Storage分页列表
     */
    @Override
    public PageResult<StorageVo> queryPageList(StorageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Storage> lqw = buildQueryWrapper(bo);
        Page<StorageVo> result = storageMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Storage列表
     *
     * @param bo 查询条件
     * @return Storage列表
     */
    @Override
    public List<StorageVo> queryList(StorageBo bo) {
        LambdaQueryWrapper<Storage> lqw = buildQueryWrapper(bo);
        return storageMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Storage> buildQueryWrapper(StorageBo bo) {
        return QueryBuilder.lambda(Storage.class)
            .eqIfText(Storage::getStorageName, bo.getStorageName())
            .eqIfText(Storage::getStorageCode, bo.getStorageCode())
            .eqIfText(Storage::getStatus, bo.getStatus())
            .orderByAsc(Storage::getId)
            .build();
    }

    /**
     * 新增Storage
     *
     * @param bo Storage
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StorageBo bo) {
        Storage add = MapstructUtils.convert(bo, Storage.class);
        validEntityBeforeSave(add);
        boolean flag = storageMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Storage
     *
     * @param bo Storage
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StorageBo bo) {
        Storage update = MapstructUtils.convert(bo, Storage.class);
        validEntityBeforeSave(update);
        return storageMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Storage entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Storage信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 可在此扩展删除前业务校验
        }
        return storageMapper.deleteByIds(ids) > 0;
    }

}
