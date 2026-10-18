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
import org.dromara.system.domain.bo.MasterApprenticeBo;
import org.dromara.system.domain.vo.MasterApprenticeVo;
import org.dromara.system.domain.MasterApprentice;
import org.dromara.system.mapper.MasterApprenticeMapper;
import org.dromara.system.service.IMasterApprenticeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * MasterApprenticeService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MasterApprenticeServiceImpl implements IMasterApprenticeService {

    private final MasterApprenticeMapper masterApprenticeMapper;

    /**
     * 查询MasterApprentice
     *
     * @param id 主键
     * @return MasterApprentice
     */
    @Override
    public MasterApprenticeVo queryById(Long id) {
        return masterApprenticeMapper.selectVoById(id);
    }

    /**
     * 分页查询MasterApprentice列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return MasterApprentice分页列表
     */
    @Override
    public PageResult<MasterApprenticeVo> queryPageList(MasterApprenticeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MasterApprentice> lqw = buildQueryWrapper(bo);
        Page<MasterApprenticeVo> result = masterApprenticeMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的MasterApprentice列表
     *
     * @param bo 查询条件
     * @return MasterApprentice列表
     */
    @Override
    public List<MasterApprenticeVo> queryList(MasterApprenticeBo bo) {
        LambdaQueryWrapper<MasterApprentice> lqw = buildQueryWrapper(bo);
        return masterApprenticeMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<MasterApprentice> buildQueryWrapper(MasterApprenticeBo bo) {
        return QueryBuilder.lambda(MasterApprentice.class)
            .eqIfText(MasterApprentice::getMasterName, bo.getMasterName())
            .eqIfText(MasterApprentice::getMasterCode, bo.getMasterCode())
            .eqIfText(MasterApprentice::getApprenticeName, bo.getApprenticeName())
            .eqIfText(MasterApprentice::getApprenticeCode, bo.getApprenticeCode())
            .eqIfText(MasterApprentice::getMasterOrgCodePath, bo.getMasterOrgCodePath())
            .eqIfText(MasterApprentice::getStatus, bo.getStatus())
            .orderByAsc(MasterApprentice::getId)
            .build();
    }

    /**
     * 新增MasterApprentice
     *
     * @param bo MasterApprentice
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(MasterApprenticeBo bo) {
        MasterApprentice add = MapstructUtils.convert(bo, MasterApprentice.class);
        validEntityBeforeSave(add);
        boolean flag = masterApprenticeMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改MasterApprentice
     *
     * @param bo MasterApprentice
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(MasterApprenticeBo bo) {
        MasterApprentice update = MapstructUtils.convert(bo, MasterApprentice.class);
        validEntityBeforeSave(update);
        return masterApprenticeMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MasterApprentice entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除MasterApprentice信息
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
        return masterApprenticeMapper.deleteByIds(ids) > 0;
    }

}
