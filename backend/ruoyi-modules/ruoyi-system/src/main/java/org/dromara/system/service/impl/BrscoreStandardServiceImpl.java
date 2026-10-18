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
import org.dromara.system.domain.bo.BrscoreStandardBo;
import org.dromara.system.domain.vo.BrscoreStandardVo;
import org.dromara.system.domain.BrscoreStandard;
import org.dromara.system.mapper.BrscoreStandardMapper;
import org.dromara.system.service.IBrscoreStandardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * BrscoreStandardService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BrscoreStandardServiceImpl implements IBrscoreStandardService {

    private final BrscoreStandardMapper brscoreStandardMapper;

    /**
     * 查询BrscoreStandard
     *
     * @param id 主键
     * @return BrscoreStandard
     */
    @Override
    public BrscoreStandardVo queryById(Long id) {
        return brscoreStandardMapper.selectVoById(id);
    }

    /**
     * 分页查询BrscoreStandard列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return BrscoreStandard分页列表
     */
    @Override
    public PageResult<BrscoreStandardVo> queryPageList(BrscoreStandardBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BrscoreStandard> lqw = buildQueryWrapper(bo);
        Page<BrscoreStandardVo> result = brscoreStandardMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的BrscoreStandard列表
     *
     * @param bo 查询条件
     * @return BrscoreStandard列表
     */
    @Override
    public List<BrscoreStandardVo> queryList(BrscoreStandardBo bo) {
        LambdaQueryWrapper<BrscoreStandard> lqw = buildQueryWrapper(bo);
        return brscoreStandardMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<BrscoreStandard> buildQueryWrapper(BrscoreStandardBo bo) {
        return QueryBuilder.lambda(BrscoreStandard.class)
            .eqIfText(BrscoreStandard::getOrgCode, bo.getOrgCode())
            .eqIfText(BrscoreStandard::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(BrscoreStandard::getStatus, bo.getStatus())
            .orderByAsc(BrscoreStandard::getId)
            .build();
    }

    /**
     * 新增BrscoreStandard
     *
     * @param bo BrscoreStandard
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BrscoreStandardBo bo) {
        BrscoreStandard add = MapstructUtils.convert(bo, BrscoreStandard.class);
        validEntityBeforeSave(add);
        boolean flag = brscoreStandardMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改BrscoreStandard
     *
     * @param bo BrscoreStandard
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BrscoreStandardBo bo) {
        BrscoreStandard update = MapstructUtils.convert(bo, BrscoreStandard.class);
        validEntityBeforeSave(update);
        return brscoreStandardMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BrscoreStandard entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除BrscoreStandard信息
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
        return brscoreStandardMapper.deleteByIds(ids) > 0;
    }

}
