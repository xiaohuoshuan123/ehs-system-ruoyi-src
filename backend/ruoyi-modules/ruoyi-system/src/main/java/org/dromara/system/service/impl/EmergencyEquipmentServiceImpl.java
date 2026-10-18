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
import org.dromara.system.domain.bo.EmergencyEquipmentBo;
import org.dromara.system.domain.vo.EmergencyEquipmentVo;
import org.dromara.system.domain.EmergencyEquipment;
import org.dromara.system.mapper.EmergencyEquipmentMapper;
import org.dromara.system.service.IEmergencyEquipmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EmergencyEquipmentService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmergencyEquipmentServiceImpl implements IEmergencyEquipmentService {

    private final EmergencyEquipmentMapper emergencyEquipmentMapper;

    /**
     * 查询EmergencyEquipment
     *
     * @param id 主键
     * @return EmergencyEquipment
     */
    @Override
    public EmergencyEquipmentVo queryById(Long id) {
        return emergencyEquipmentMapper.selectVoById(id);
    }

    /**
     * 分页查询EmergencyEquipment列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyEquipment分页列表
     */
    @Override
    public PageResult<EmergencyEquipmentVo> queryPageList(EmergencyEquipmentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EmergencyEquipment> lqw = buildQueryWrapper(bo);
        Page<EmergencyEquipmentVo> result = emergencyEquipmentMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EmergencyEquipment列表
     *
     * @param bo 查询条件
     * @return EmergencyEquipment列表
     */
    @Override
    public List<EmergencyEquipmentVo> queryList(EmergencyEquipmentBo bo) {
        LambdaQueryWrapper<EmergencyEquipment> lqw = buildQueryWrapper(bo);
        return emergencyEquipmentMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EmergencyEquipment> buildQueryWrapper(EmergencyEquipmentBo bo) {
        return QueryBuilder.lambda(EmergencyEquipment.class)
            .eqIfText(EmergencyEquipment::getEquipmentName, bo.getEquipmentName())
            .eqIfText(EmergencyEquipment::getStatus, bo.getStatus())
            .orderByAsc(EmergencyEquipment::getId)
            .build();
    }

    /**
     * 新增EmergencyEquipment
     *
     * @param bo EmergencyEquipment
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EmergencyEquipmentBo bo) {
        EmergencyEquipment add = MapstructUtils.convert(bo, EmergencyEquipment.class);
        validEntityBeforeSave(add);
        boolean flag = emergencyEquipmentMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EmergencyEquipment
     *
     * @param bo EmergencyEquipment
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EmergencyEquipmentBo bo) {
        EmergencyEquipment update = MapstructUtils.convert(bo, EmergencyEquipment.class);
        validEntityBeforeSave(update);
        return emergencyEquipmentMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EmergencyEquipment entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EmergencyEquipment信息
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
        return emergencyEquipmentMapper.deleteByIds(ids) > 0;
    }

}
