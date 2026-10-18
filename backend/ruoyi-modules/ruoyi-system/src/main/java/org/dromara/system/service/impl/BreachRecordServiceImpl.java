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
import org.dromara.system.domain.bo.BreachRecordBo;
import org.dromara.system.domain.vo.BreachRecordVo;
import org.dromara.system.domain.BreachRecord;
import org.dromara.system.mapper.BreachRecordMapper;
import org.dromara.system.service.IBreachRecordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * BreachRecordService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BreachRecordServiceImpl implements IBreachRecordService {

    private final BreachRecordMapper breachRecordMapper;

    /**
     * 查询BreachRecord
     *
     * @param id 主键
     * @return BreachRecord
     */
    @Override
    public BreachRecordVo queryById(Long id) {
        return breachRecordMapper.selectVoById(id);
    }

    /**
     * 分页查询BreachRecord列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return BreachRecord分页列表
     */
    @Override
    public PageResult<BreachRecordVo> queryPageList(BreachRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BreachRecord> lqw = buildQueryWrapper(bo);
        Page<BreachRecordVo> result = breachRecordMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的BreachRecord列表
     *
     * @param bo 查询条件
     * @return BreachRecord列表
     */
    @Override
    public List<BreachRecordVo> queryList(BreachRecordBo bo) {
        LambdaQueryWrapper<BreachRecord> lqw = buildQueryWrapper(bo);
        return breachRecordMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<BreachRecord> buildQueryWrapper(BreachRecordBo bo) {
        return QueryBuilder.lambda(BreachRecord.class)
            .eqIfText(BreachRecord::getCheckOrgCode, bo.getCheckOrgCode())
            .eqIfText(BreachRecord::getCheckOrgCodePath, bo.getCheckOrgCodePath())
            .eqIfText(BreachRecord::getStatus, bo.getStatus())
            .orderByAsc(BreachRecord::getId)
            .build();
    }

    /**
     * 新增BreachRecord
     *
     * @param bo BreachRecord
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BreachRecordBo bo) {
        BreachRecord add = MapstructUtils.convert(bo, BreachRecord.class);
        validEntityBeforeSave(add);
        boolean flag = breachRecordMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改BreachRecord
     *
     * @param bo BreachRecord
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BreachRecordBo bo) {
        BreachRecord update = MapstructUtils.convert(bo, BreachRecord.class);
        validEntityBeforeSave(update);
        return breachRecordMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BreachRecord entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除BreachRecord信息
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
        return breachRecordMapper.deleteByIds(ids) > 0;
    }

}
