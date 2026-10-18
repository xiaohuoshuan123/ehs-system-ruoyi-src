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
import org.dromara.system.domain.bo.AccidentBo;
import org.dromara.system.domain.vo.AccidentVo;
import org.dromara.system.domain.Accident;
import org.dromara.system.mapper.AccidentMapper;
import org.dromara.system.service.IAccidentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * AccidentService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AccidentServiceImpl implements IAccidentService {

    private final AccidentMapper accidentMapper;

    /**
     * 查询Accident
     *
     * @param id 主键
     * @return Accident
     */
    @Override
    public AccidentVo queryById(Long id) {
        return accidentMapper.selectVoById(id);
    }

    /**
     * 分页查询Accident列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Accident分页列表
     */
    @Override
    public PageResult<AccidentVo> queryPageList(AccidentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Accident> lqw = buildQueryWrapper(bo);
        Page<AccidentVo> result = accidentMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Accident列表
     *
     * @param bo 查询条件
     * @return Accident列表
     */
    @Override
    public List<AccidentVo> queryList(AccidentBo bo) {
        LambdaQueryWrapper<Accident> lqw = buildQueryWrapper(bo);
        return accidentMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Accident> buildQueryWrapper(AccidentBo bo) {
        return QueryBuilder.lambda(Accident.class)
            .eqIfText(Accident::getTheirOrgCode, bo.getTheirOrgCode())
            .eqIfText(Accident::getAccidentName, bo.getAccidentName())
            .eqIfText(Accident::getStatus, bo.getStatus())
            .orderByAsc(Accident::getId)
            .build();
    }

    /**
     * 新增Accident
     *
     * @param bo Accident
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AccidentBo bo) {
        Accident add = MapstructUtils.convert(bo, Accident.class);
        validEntityBeforeSave(add);
        boolean flag = accidentMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Accident
     *
     * @param bo Accident
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AccidentBo bo) {
        Accident update = MapstructUtils.convert(bo, Accident.class);
        validEntityBeforeSave(update);
        return accidentMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Accident entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Accident信息
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
        return accidentMapper.deleteByIds(ids) > 0;
    }

}
