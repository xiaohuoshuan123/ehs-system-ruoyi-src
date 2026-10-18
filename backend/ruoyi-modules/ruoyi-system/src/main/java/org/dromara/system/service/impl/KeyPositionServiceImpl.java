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
import org.dromara.system.domain.bo.KeyPositionBo;
import org.dromara.system.domain.vo.KeyPositionVo;
import org.dromara.system.domain.KeyPosition;
import org.dromara.system.mapper.KeyPositionMapper;
import org.dromara.system.service.IKeyPositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * KeyPositionService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class KeyPositionServiceImpl implements IKeyPositionService {

    private final KeyPositionMapper keyPositionMapper;

    /**
     * 查询KeyPosition
     *
     * @param id 主键
     * @return KeyPosition
     */
    @Override
    public KeyPositionVo queryById(Long id) {
        return keyPositionMapper.selectVoById(id);
    }

    /**
     * 分页查询KeyPosition列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return KeyPosition分页列表
     */
    @Override
    public PageResult<KeyPositionVo> queryPageList(KeyPositionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<KeyPosition> lqw = buildQueryWrapper(bo);
        Page<KeyPositionVo> result = keyPositionMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的KeyPosition列表
     *
     * @param bo 查询条件
     * @return KeyPosition列表
     */
    @Override
    public List<KeyPositionVo> queryList(KeyPositionBo bo) {
        LambdaQueryWrapper<KeyPosition> lqw = buildQueryWrapper(bo);
        return keyPositionMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<KeyPosition> buildQueryWrapper(KeyPositionBo bo) {
        return QueryBuilder.lambda(KeyPosition.class)
            .eqIfText(KeyPosition::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(KeyPosition::getStatus, bo.getStatus())
            .orderByAsc(KeyPosition::getId)
            .build();
    }

    /**
     * 新增KeyPosition
     *
     * @param bo KeyPosition
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(KeyPositionBo bo) {
        KeyPosition add = MapstructUtils.convert(bo, KeyPosition.class);
        validEntityBeforeSave(add);
        boolean flag = keyPositionMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改KeyPosition
     *
     * @param bo KeyPosition
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(KeyPositionBo bo) {
        KeyPosition update = MapstructUtils.convert(bo, KeyPosition.class);
        validEntityBeforeSave(update);
        return keyPositionMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(KeyPosition entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除KeyPosition信息
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
        return keyPositionMapper.deleteByIds(ids) > 0;
    }

}
