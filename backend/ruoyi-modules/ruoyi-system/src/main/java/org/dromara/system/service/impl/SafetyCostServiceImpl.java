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
import org.dromara.system.domain.bo.SafetyCostBo;
import org.dromara.system.domain.vo.SafetyCostVo;
import org.dromara.system.domain.SafetyCost;
import org.dromara.system.mapper.SafetyCostMapper;
import org.dromara.system.service.ISafetyCostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SafetyCostService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SafetyCostServiceImpl implements ISafetyCostService {

    private final SafetyCostMapper safetyCostMapper;

    /**
     * 查询SafetyCost
     *
     * @param id 主键
     * @return SafetyCost
     */
    @Override
    public SafetyCostVo queryById(Long id) {
        return safetyCostMapper.selectVoById(id);
    }

    /**
     * 分页查询SafetyCost列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyCost分页列表
     */
    @Override
    public PageResult<SafetyCostVo> queryPageList(SafetyCostBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SafetyCost> lqw = buildQueryWrapper(bo);
        Page<SafetyCostVo> result = safetyCostMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SafetyCost列表
     *
     * @param bo 查询条件
     * @return SafetyCost列表
     */
    @Override
    public List<SafetyCostVo> queryList(SafetyCostBo bo) {
        LambdaQueryWrapper<SafetyCost> lqw = buildQueryWrapper(bo);
        return safetyCostMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SafetyCost> buildQueryWrapper(SafetyCostBo bo) {
        return QueryBuilder.lambda(SafetyCost.class)
            .eqIfText(SafetyCost::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(SafetyCost::getCostName, bo.getCostName())
            .eqIfText(SafetyCost::getStatus, bo.getStatus())
            .orderByAsc(SafetyCost::getId)
            .build();
    }

    /**
     * 新增SafetyCost
     *
     * @param bo SafetyCost
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SafetyCostBo bo) {
        SafetyCost add = MapstructUtils.convert(bo, SafetyCost.class);
        validEntityBeforeSave(add);
        boolean flag = safetyCostMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SafetyCost
     *
     * @param bo SafetyCost
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SafetyCostBo bo) {
        SafetyCost update = MapstructUtils.convert(bo, SafetyCost.class);
        validEntityBeforeSave(update);
        return safetyCostMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SafetyCost entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SafetyCost信息
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
        return safetyCostMapper.deleteByIds(ids) > 0;
    }

}
