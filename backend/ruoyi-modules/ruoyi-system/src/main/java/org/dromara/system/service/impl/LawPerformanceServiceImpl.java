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
import org.dromara.system.domain.bo.LawPerformanceBo;
import org.dromara.system.domain.vo.LawPerformanceVo;
import org.dromara.system.domain.LawPerformance;
import org.dromara.system.mapper.LawPerformanceMapper;
import org.dromara.system.service.ILawPerformanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * LawPerformanceService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LawPerformanceServiceImpl implements ILawPerformanceService {

    private final LawPerformanceMapper lawPerformanceMapper;

    /**
     * 查询LawPerformance
     *
     * @param id 主键
     * @return LawPerformance
     */
    @Override
    public LawPerformanceVo queryById(Long id) {
        return lawPerformanceMapper.selectVoById(id);
    }

    /**
     * 分页查询LawPerformance列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return LawPerformance分页列表
     */
    @Override
    public PageResult<LawPerformanceVo> queryPageList(LawPerformanceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<LawPerformance> lqw = buildQueryWrapper(bo);
        Page<LawPerformanceVo> result = lawPerformanceMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的LawPerformance列表
     *
     * @param bo 查询条件
     * @return LawPerformance列表
     */
    @Override
    public List<LawPerformanceVo> queryList(LawPerformanceBo bo) {
        LambdaQueryWrapper<LawPerformance> lqw = buildQueryWrapper(bo);
        return lawPerformanceMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<LawPerformance> buildQueryWrapper(LawPerformanceBo bo) {
        return QueryBuilder.lambda(LawPerformance.class)
            .eqIfText(LawPerformance::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(LawPerformance::getStatus, bo.getStatus())
            .orderByAsc(LawPerformance::getId)
            .build();
    }

    /**
     * 新增LawPerformance
     *
     * @param bo LawPerformance
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(LawPerformanceBo bo) {
        LawPerformance add = MapstructUtils.convert(bo, LawPerformance.class);
        validEntityBeforeSave(add);
        boolean flag = lawPerformanceMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改LawPerformance
     *
     * @param bo LawPerformance
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(LawPerformanceBo bo) {
        LawPerformance update = MapstructUtils.convert(bo, LawPerformance.class);
        validEntityBeforeSave(update);
        return lawPerformanceMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(LawPerformance entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除LawPerformance信息
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
        return lawPerformanceMapper.deleteByIds(ids) > 0;
    }

}
