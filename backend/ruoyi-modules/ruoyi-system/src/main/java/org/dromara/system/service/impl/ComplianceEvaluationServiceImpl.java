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
import org.dromara.system.domain.bo.ComplianceEvaluationBo;
import org.dromara.system.domain.vo.ComplianceEvaluationVo;
import org.dromara.system.domain.ComplianceEvaluation;
import org.dromara.system.mapper.ComplianceEvaluationMapper;
import org.dromara.system.service.IComplianceEvaluationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * ComplianceEvaluationService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ComplianceEvaluationServiceImpl implements IComplianceEvaluationService {

    private final ComplianceEvaluationMapper complianceEvaluationMapper;

    /**
     * 查询ComplianceEvaluation
     *
     * @param id 主键
     * @return ComplianceEvaluation
     */
    @Override
    public ComplianceEvaluationVo queryById(Long id) {
        return complianceEvaluationMapper.selectVoById(id);
    }

    /**
     * 分页查询ComplianceEvaluation列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ComplianceEvaluation分页列表
     */
    @Override
    public PageResult<ComplianceEvaluationVo> queryPageList(ComplianceEvaluationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ComplianceEvaluation> lqw = buildQueryWrapper(bo);
        Page<ComplianceEvaluationVo> result = complianceEvaluationMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的ComplianceEvaluation列表
     *
     * @param bo 查询条件
     * @return ComplianceEvaluation列表
     */
    @Override
    public List<ComplianceEvaluationVo> queryList(ComplianceEvaluationBo bo) {
        LambdaQueryWrapper<ComplianceEvaluation> lqw = buildQueryWrapper(bo);
        return complianceEvaluationMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<ComplianceEvaluation> buildQueryWrapper(ComplianceEvaluationBo bo) {
        return QueryBuilder.lambda(ComplianceEvaluation.class)
            .eqIfText(ComplianceEvaluation::getManageOrgCode, bo.getManageOrgCode())
            .eqIfText(ComplianceEvaluation::getStatus, bo.getStatus())
            .orderByAsc(ComplianceEvaluation::getId)
            .build();
    }

    /**
     * 新增ComplianceEvaluation
     *
     * @param bo ComplianceEvaluation
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ComplianceEvaluationBo bo) {
        ComplianceEvaluation add = MapstructUtils.convert(bo, ComplianceEvaluation.class);
        validEntityBeforeSave(add);
        boolean flag = complianceEvaluationMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改ComplianceEvaluation
     *
     * @param bo ComplianceEvaluation
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ComplianceEvaluationBo bo) {
        ComplianceEvaluation update = MapstructUtils.convert(bo, ComplianceEvaluation.class);
        validEntityBeforeSave(update);
        return complianceEvaluationMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ComplianceEvaluation entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除ComplianceEvaluation信息
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
        return complianceEvaluationMapper.deleteByIds(ids) > 0;
    }

}
