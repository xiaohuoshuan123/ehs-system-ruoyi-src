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
import org.dromara.system.domain.bo.CompanyRegulationBo;
import org.dromara.system.domain.vo.CompanyRegulationVo;
import org.dromara.system.domain.CompanyRegulation;
import org.dromara.system.mapper.CompanyRegulationMapper;
import org.dromara.system.service.ICompanyRegulationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * CompanyRegulationService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyRegulationServiceImpl implements ICompanyRegulationService {

    private final CompanyRegulationMapper companyRegulationMapper;

    /**
     * 查询CompanyRegulation
     *
     * @param id 主键
     * @return CompanyRegulation
     */
    @Override
    public CompanyRegulationVo queryById(Long id) {
        return companyRegulationMapper.selectVoById(id);
    }

    /**
     * 分页查询CompanyRegulation列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return CompanyRegulation分页列表
     */
    @Override
    public PageResult<CompanyRegulationVo> queryPageList(CompanyRegulationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CompanyRegulation> lqw = buildQueryWrapper(bo);
        Page<CompanyRegulationVo> result = companyRegulationMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的CompanyRegulation列表
     *
     * @param bo 查询条件
     * @return CompanyRegulation列表
     */
    @Override
    public List<CompanyRegulationVo> queryList(CompanyRegulationBo bo) {
        LambdaQueryWrapper<CompanyRegulation> lqw = buildQueryWrapper(bo);
        return companyRegulationMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<CompanyRegulation> buildQueryWrapper(CompanyRegulationBo bo) {
        return QueryBuilder.lambda(CompanyRegulation.class)
            .eqIfText(CompanyRegulation::getManageOrgCode, bo.getManageOrgCode())
            .eqIfText(CompanyRegulation::getManageOrgName, bo.getManageOrgName())
            .eqIfText(CompanyRegulation::getStatus, bo.getStatus())
            .orderByAsc(CompanyRegulation::getId)
            .build();
    }

    /**
     * 新增CompanyRegulation
     *
     * @param bo CompanyRegulation
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(CompanyRegulationBo bo) {
        CompanyRegulation add = MapstructUtils.convert(bo, CompanyRegulation.class);
        validEntityBeforeSave(add);
        boolean flag = companyRegulationMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改CompanyRegulation
     *
     * @param bo CompanyRegulation
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(CompanyRegulationBo bo) {
        CompanyRegulation update = MapstructUtils.convert(bo, CompanyRegulation.class);
        validEntityBeforeSave(update);
        return companyRegulationMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CompanyRegulation entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除CompanyRegulation信息
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
        return companyRegulationMapper.deleteByIds(ids) > 0;
    }

}
