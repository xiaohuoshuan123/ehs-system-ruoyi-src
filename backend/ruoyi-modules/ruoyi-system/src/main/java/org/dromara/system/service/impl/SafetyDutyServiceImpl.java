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
import org.dromara.system.domain.bo.SafetyDutyBo;
import org.dromara.system.domain.vo.SafetyDutyVo;
import org.dromara.system.domain.SafetyDuty;
import org.dromara.system.mapper.SafetyDutyMapper;
import org.dromara.system.service.ISafetyDutyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SafetyDutyService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SafetyDutyServiceImpl implements ISafetyDutyService {

    private final SafetyDutyMapper safetyDutyMapper;

    /**
     * 查询SafetyDuty
     *
     * @param id 主键
     * @return SafetyDuty
     */
    @Override
    public SafetyDutyVo queryById(Long id) {
        return safetyDutyMapper.selectVoById(id);
    }

    /**
     * 分页查询SafetyDuty列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyDuty分页列表
     */
    @Override
    public PageResult<SafetyDutyVo> queryPageList(SafetyDutyBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SafetyDuty> lqw = buildQueryWrapper(bo);
        Page<SafetyDutyVo> result = safetyDutyMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SafetyDuty列表
     *
     * @param bo 查询条件
     * @return SafetyDuty列表
     */
    @Override
    public List<SafetyDutyVo> queryList(SafetyDutyBo bo) {
        LambdaQueryWrapper<SafetyDuty> lqw = buildQueryWrapper(bo);
        return safetyDutyMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SafetyDuty> buildQueryWrapper(SafetyDutyBo bo) {
        return QueryBuilder.lambda(SafetyDuty.class)
            .eqIfText(SafetyDuty::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(SafetyDuty::getOrgCode, bo.getOrgCode())
            .eqIfText(SafetyDuty::getOrgName, bo.getOrgName())
            .eqIfText(SafetyDuty::getJobName, bo.getJobName())
            .eqIfText(SafetyDuty::getStatus, bo.getStatus())
            .orderByAsc(SafetyDuty::getId)
            .build();
    }

    /**
     * 新增SafetyDuty
     *
     * @param bo SafetyDuty
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SafetyDutyBo bo) {
        SafetyDuty add = MapstructUtils.convert(bo, SafetyDuty.class);
        validEntityBeforeSave(add);
        boolean flag = safetyDutyMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SafetyDuty
     *
     * @param bo SafetyDuty
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SafetyDutyBo bo) {
        SafetyDuty update = MapstructUtils.convert(bo, SafetyDuty.class);
        validEntityBeforeSave(update);
        return safetyDutyMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SafetyDuty entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SafetyDuty信息
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
        return safetyDutyMapper.deleteByIds(ids) > 0;
    }

}
