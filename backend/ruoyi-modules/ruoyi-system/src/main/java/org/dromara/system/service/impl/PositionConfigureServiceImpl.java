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
import org.dromara.system.domain.bo.PositionConfigureBo;
import org.dromara.system.domain.vo.PositionConfigureVo;
import org.dromara.system.domain.PositionConfigure;
import org.dromara.system.mapper.PositionConfigureMapper;
import org.dromara.system.service.IPositionConfigureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * PositionConfigureService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PositionConfigureServiceImpl implements IPositionConfigureService {

    private final PositionConfigureMapper positionConfigureMapper;

    /**
     * 查询PositionConfigure
     *
     * @param id 主键
     * @return PositionConfigure
     */
    @Override
    public PositionConfigureVo queryById(Long id) {
        return positionConfigureMapper.selectVoById(id);
    }

    /**
     * 分页查询PositionConfigure列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return PositionConfigure分页列表
     */
    @Override
    public PageResult<PositionConfigureVo> queryPageList(PositionConfigureBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PositionConfigure> lqw = buildQueryWrapper(bo);
        Page<PositionConfigureVo> result = positionConfigureMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的PositionConfigure列表
     *
     * @param bo 查询条件
     * @return PositionConfigure列表
     */
    @Override
    public List<PositionConfigureVo> queryList(PositionConfigureBo bo) {
        LambdaQueryWrapper<PositionConfigure> lqw = buildQueryWrapper(bo);
        return positionConfigureMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<PositionConfigure> buildQueryWrapper(PositionConfigureBo bo) {
        return QueryBuilder.lambda(PositionConfigure.class)
            .eqIfText(PositionConfigure::getPositionName, bo.getPositionName())
            .eqIfText(PositionConfigure::getCheckOrgCodePath, bo.getCheckOrgCodePath())
            .eqIfText(PositionConfigure::getStatus, bo.getStatus())
            .orderByAsc(PositionConfigure::getId)
            .build();
    }

    /**
     * 新增PositionConfigure
     *
     * @param bo PositionConfigure
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(PositionConfigureBo bo) {
        PositionConfigure add = MapstructUtils.convert(bo, PositionConfigure.class);
        validEntityBeforeSave(add);
        boolean flag = positionConfigureMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改PositionConfigure
     *
     * @param bo PositionConfigure
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(PositionConfigureBo bo) {
        PositionConfigure update = MapstructUtils.convert(bo, PositionConfigure.class);
        validEntityBeforeSave(update);
        return positionConfigureMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PositionConfigure entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除PositionConfigure信息
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
        return positionConfigureMapper.deleteByIds(ids) > 0;
    }

}
