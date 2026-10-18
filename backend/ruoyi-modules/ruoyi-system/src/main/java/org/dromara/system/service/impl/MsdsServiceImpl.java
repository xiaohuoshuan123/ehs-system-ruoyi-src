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
import org.dromara.system.domain.bo.MsdsBo;
import org.dromara.system.domain.vo.MsdsVo;
import org.dromara.system.domain.Msds;
import org.dromara.system.mapper.MsdsMapper;
import org.dromara.system.service.IMsdsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * MsdsService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MsdsServiceImpl implements IMsdsService {

    private final MsdsMapper msdsMapper;

    /**
     * 查询Msds
     *
     * @param id 主键
     * @return Msds
     */
    @Override
    public MsdsVo queryById(Long id) {
        return msdsMapper.selectVoById(id);
    }

    /**
     * 分页查询Msds列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Msds分页列表
     */
    @Override
    public PageResult<MsdsVo> queryPageList(MsdsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Msds> lqw = buildQueryWrapper(bo);
        Page<MsdsVo> result = msdsMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Msds列表
     *
     * @param bo 查询条件
     * @return Msds列表
     */
    @Override
    public List<MsdsVo> queryList(MsdsBo bo) {
        LambdaQueryWrapper<Msds> lqw = buildQueryWrapper(bo);
        return msdsMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Msds> buildQueryWrapper(MsdsBo bo) {
        return QueryBuilder.lambda(Msds.class)
            .eqIfText(Msds::getArticleCode, bo.getArticleCode())
            .eqIfText(Msds::getArticleName, bo.getArticleName())
            .eqIfText(Msds::getIssueOrgCode, bo.getIssueOrgCode())
            .eqIfText(Msds::getStatus, bo.getStatus())
            .orderByAsc(Msds::getId)
            .build();
    }

    /**
     * 新增Msds
     *
     * @param bo Msds
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(MsdsBo bo) {
        Msds add = MapstructUtils.convert(bo, Msds.class);
        validEntityBeforeSave(add);
        boolean flag = msdsMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Msds
     *
     * @param bo Msds
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(MsdsBo bo) {
        Msds update = MapstructUtils.convert(bo, Msds.class);
        validEntityBeforeSave(update);
        return msdsMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Msds entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Msds信息
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
        return msdsMapper.deleteByIds(ids) > 0;
    }

}
