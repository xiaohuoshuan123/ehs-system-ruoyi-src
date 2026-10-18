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
import org.dromara.system.domain.bo.EmergencyDrillBo;
import org.dromara.system.domain.vo.EmergencyDrillVo;
import org.dromara.system.domain.EmergencyDrill;
import org.dromara.system.mapper.EmergencyDrillMapper;
import org.dromara.system.service.IEmergencyDrillService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EmergencyDrillService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmergencyDrillServiceImpl implements IEmergencyDrillService {

    private final EmergencyDrillMapper emergencyDrillMapper;

    /**
     * 查询EmergencyDrill
     *
     * @param id 主键
     * @return EmergencyDrill
     */
    @Override
    public EmergencyDrillVo queryById(Long id) {
        return emergencyDrillMapper.selectVoById(id);
    }

    /**
     * 分页查询EmergencyDrill列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyDrill分页列表
     */
    @Override
    public PageResult<EmergencyDrillVo> queryPageList(EmergencyDrillBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EmergencyDrill> lqw = buildQueryWrapper(bo);
        Page<EmergencyDrillVo> result = emergencyDrillMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EmergencyDrill列表
     *
     * @param bo 查询条件
     * @return EmergencyDrill列表
     */
    @Override
    public List<EmergencyDrillVo> queryList(EmergencyDrillBo bo) {
        LambdaQueryWrapper<EmergencyDrill> lqw = buildQueryWrapper(bo);
        return emergencyDrillMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EmergencyDrill> buildQueryWrapper(EmergencyDrillBo bo) {
        return QueryBuilder.lambda(EmergencyDrill.class)
            .eqIfText(EmergencyDrill::getEmergencyName, bo.getEmergencyName())
            .eqIfText(EmergencyDrill::getOrgCodes, bo.getOrgCodes())
            .eqIfText(EmergencyDrill::getStatus, bo.getStatus())
            .orderByAsc(EmergencyDrill::getId)
            .build();
    }

    /**
     * 新增EmergencyDrill
     *
     * @param bo EmergencyDrill
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EmergencyDrillBo bo) {
        EmergencyDrill add = MapstructUtils.convert(bo, EmergencyDrill.class);
        validEntityBeforeSave(add);
        boolean flag = emergencyDrillMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EmergencyDrill
     *
     * @param bo EmergencyDrill
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EmergencyDrillBo bo) {
        EmergencyDrill update = MapstructUtils.convert(bo, EmergencyDrill.class);
        validEntityBeforeSave(update);
        return emergencyDrillMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EmergencyDrill entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EmergencyDrill信息
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
        return emergencyDrillMapper.deleteByIds(ids) > 0;
    }

}
