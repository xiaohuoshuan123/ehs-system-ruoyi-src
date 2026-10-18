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
import org.dromara.system.domain.bo.EmergencyPointBo;
import org.dromara.system.domain.vo.EmergencyPointVo;
import org.dromara.system.domain.EmergencyPoint;
import org.dromara.system.mapper.EmergencyPointMapper;
import org.dromara.system.service.IEmergencyPointService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EmergencyPointService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmergencyPointServiceImpl implements IEmergencyPointService {

    private final EmergencyPointMapper emergencyPointMapper;

    /**
     * 查询EmergencyPoint
     *
     * @param id 主键
     * @return EmergencyPoint
     */
    @Override
    public EmergencyPointVo queryById(Long id) {
        return emergencyPointMapper.selectVoById(id);
    }

    /**
     * 分页查询EmergencyPoint列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyPoint分页列表
     */
    @Override
    public PageResult<EmergencyPointVo> queryPageList(EmergencyPointBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EmergencyPoint> lqw = buildQueryWrapper(bo);
        Page<EmergencyPointVo> result = emergencyPointMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EmergencyPoint列表
     *
     * @param bo 查询条件
     * @return EmergencyPoint列表
     */
    @Override
    public List<EmergencyPointVo> queryList(EmergencyPointBo bo) {
        LambdaQueryWrapper<EmergencyPoint> lqw = buildQueryWrapper(bo);
        return emergencyPointMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EmergencyPoint> buildQueryWrapper(EmergencyPointBo bo) {
        return QueryBuilder.lambda(EmergencyPoint.class)
            .eqIfText(EmergencyPoint::getDutyOrgCode, bo.getDutyOrgCode())
            .eqIfText(EmergencyPoint::getPointName, bo.getPointName())
            .eqIfText(EmergencyPoint::getStatus, bo.getStatus())
            .orderByAsc(EmergencyPoint::getId)
            .build();
    }

    /**
     * 新增EmergencyPoint
     *
     * @param bo EmergencyPoint
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EmergencyPointBo bo) {
        EmergencyPoint add = MapstructUtils.convert(bo, EmergencyPoint.class);
        validEntityBeforeSave(add);
        boolean flag = emergencyPointMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EmergencyPoint
     *
     * @param bo EmergencyPoint
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EmergencyPointBo bo) {
        EmergencyPoint update = MapstructUtils.convert(bo, EmergencyPoint.class);
        validEntityBeforeSave(update);
        return emergencyPointMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EmergencyPoint entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EmergencyPoint信息
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
        return emergencyPointMapper.deleteByIds(ids) > 0;
    }

}
