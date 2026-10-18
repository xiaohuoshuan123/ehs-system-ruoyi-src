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
import org.dromara.system.domain.bo.EmergencyTeamBo;
import org.dromara.system.domain.vo.EmergencyTeamVo;
import org.dromara.system.domain.EmergencyTeam;
import org.dromara.system.mapper.EmergencyTeamMapper;
import org.dromara.system.service.IEmergencyTeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EmergencyTeamService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmergencyTeamServiceImpl implements IEmergencyTeamService {

    private final EmergencyTeamMapper emergencyTeamMapper;

    /**
     * 查询EmergencyTeam
     *
     * @param id 主键
     * @return EmergencyTeam
     */
    @Override
    public EmergencyTeamVo queryById(Long id) {
        return emergencyTeamMapper.selectVoById(id);
    }

    /**
     * 分页查询EmergencyTeam列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyTeam分页列表
     */
    @Override
    public PageResult<EmergencyTeamVo> queryPageList(EmergencyTeamBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EmergencyTeam> lqw = buildQueryWrapper(bo);
        Page<EmergencyTeamVo> result = emergencyTeamMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EmergencyTeam列表
     *
     * @param bo 查询条件
     * @return EmergencyTeam列表
     */
    @Override
    public List<EmergencyTeamVo> queryList(EmergencyTeamBo bo) {
        LambdaQueryWrapper<EmergencyTeam> lqw = buildQueryWrapper(bo);
        return emergencyTeamMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EmergencyTeam> buildQueryWrapper(EmergencyTeamBo bo) {
        return QueryBuilder.lambda(EmergencyTeam.class)
            .eqIfText(EmergencyTeam::getOrgCodeId, bo.getOrgCodeId())
            .eqIfText(EmergencyTeam::getTeamName, bo.getTeamName())
            .eqIfText(EmergencyTeam::getStatus, bo.getStatus())
            .orderByAsc(EmergencyTeam::getId)
            .build();
    }

    /**
     * 新增EmergencyTeam
     *
     * @param bo EmergencyTeam
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EmergencyTeamBo bo) {
        EmergencyTeam add = MapstructUtils.convert(bo, EmergencyTeam.class);
        validEntityBeforeSave(add);
        boolean flag = emergencyTeamMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EmergencyTeam
     *
     * @param bo EmergencyTeam
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EmergencyTeamBo bo) {
        EmergencyTeam update = MapstructUtils.convert(bo, EmergencyTeam.class);
        validEntityBeforeSave(update);
        return emergencyTeamMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EmergencyTeam entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EmergencyTeam信息
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
        return emergencyTeamMapper.deleteByIds(ids) > 0;
    }

}
