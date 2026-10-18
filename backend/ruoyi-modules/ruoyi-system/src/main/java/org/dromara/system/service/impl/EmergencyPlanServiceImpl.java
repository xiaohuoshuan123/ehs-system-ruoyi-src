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
import org.dromara.system.domain.bo.EmergencyPlanBo;
import org.dromara.system.domain.vo.EmergencyPlanVo;
import org.dromara.system.domain.EmergencyPlan;
import org.dromara.system.mapper.EmergencyPlanMapper;
import org.dromara.system.service.IEmergencyPlanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EmergencyPlanService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmergencyPlanServiceImpl implements IEmergencyPlanService {

    private final EmergencyPlanMapper emergencyPlanMapper;

    /**
     * 查询EmergencyPlan
     *
     * @param id 主键
     * @return EmergencyPlan
     */
    @Override
    public EmergencyPlanVo queryById(Long id) {
        return emergencyPlanMapper.selectVoById(id);
    }

    /**
     * 分页查询EmergencyPlan列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyPlan分页列表
     */
    @Override
    public PageResult<EmergencyPlanVo> queryPageList(EmergencyPlanBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EmergencyPlan> lqw = buildQueryWrapper(bo);
        Page<EmergencyPlanVo> result = emergencyPlanMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EmergencyPlan列表
     *
     * @param bo 查询条件
     * @return EmergencyPlan列表
     */
    @Override
    public List<EmergencyPlanVo> queryList(EmergencyPlanBo bo) {
        LambdaQueryWrapper<EmergencyPlan> lqw = buildQueryWrapper(bo);
        return emergencyPlanMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EmergencyPlan> buildQueryWrapper(EmergencyPlanBo bo) {
        return QueryBuilder.lambda(EmergencyPlan.class)
            .eqIfText(EmergencyPlan::getEmerName, bo.getEmerName())
            .eqIfText(EmergencyPlan::getOrgCodes, bo.getOrgCodes())
            .eqIfText(EmergencyPlan::getStatus, bo.getStatus())
            .orderByAsc(EmergencyPlan::getId)
            .build();
    }

    /**
     * 新增EmergencyPlan
     *
     * @param bo EmergencyPlan
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EmergencyPlanBo bo) {
        EmergencyPlan add = MapstructUtils.convert(bo, EmergencyPlan.class);
        validEntityBeforeSave(add);
        boolean flag = emergencyPlanMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EmergencyPlan
     *
     * @param bo EmergencyPlan
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EmergencyPlanBo bo) {
        EmergencyPlan update = MapstructUtils.convert(bo, EmergencyPlan.class);
        validEntityBeforeSave(update);
        return emergencyPlanMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EmergencyPlan entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EmergencyPlan信息
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
        return emergencyPlanMapper.deleteByIds(ids) > 0;
    }

}
