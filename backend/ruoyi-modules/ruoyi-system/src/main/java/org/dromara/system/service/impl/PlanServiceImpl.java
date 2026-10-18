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
import org.dromara.system.domain.bo.PlanBo;
import org.dromara.system.domain.vo.PlanVo;
import org.dromara.system.domain.Plan;
import org.dromara.system.mapper.PlanMapper;
import org.dromara.system.service.IPlanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * PlanService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements IPlanService {

    private final PlanMapper planMapper;

    /**
     * 查询Plan
     *
     * @param id 主键
     * @return Plan
     */
    @Override
    public PlanVo queryById(Long id) {
        return planMapper.selectVoById(id);
    }

    /**
     * 分页查询Plan列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Plan分页列表
     */
    @Override
    public PageResult<PlanVo> queryPageList(PlanBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Plan> lqw = buildQueryWrapper(bo);
        Page<PlanVo> result = planMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Plan列表
     *
     * @param bo 查询条件
     * @return Plan列表
     */
    @Override
    public List<PlanVo> queryList(PlanBo bo) {
        LambdaQueryWrapper<Plan> lqw = buildQueryWrapper(bo);
        return planMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Plan> buildQueryWrapper(PlanBo bo) {
        return QueryBuilder.lambda(Plan.class)
            .eqIfText(Plan::getOrgCode, bo.getOrgCode())
            .eqIfText(Plan::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(Plan::getStatus, bo.getStatus())
            .orderByAsc(Plan::getId)
            .build();
    }

    /**
     * 新增Plan
     *
     * @param bo Plan
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(PlanBo bo) {
        Plan add = MapstructUtils.convert(bo, Plan.class);
        validEntityBeforeSave(add);
        boolean flag = planMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Plan
     *
     * @param bo Plan
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(PlanBo bo) {
        Plan update = MapstructUtils.convert(bo, Plan.class);
        validEntityBeforeSave(update);
        return planMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Plan entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Plan信息
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
        return planMapper.deleteByIds(ids) > 0;
    }

}
