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
import org.dromara.system.domain.bo.EnterprisePersonBo;
import org.dromara.system.domain.vo.EnterprisePersonVo;
import org.dromara.system.domain.EnterprisePerson;
import org.dromara.system.mapper.EnterprisePersonMapper;
import org.dromara.system.service.IEnterprisePersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EnterprisePersonService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EnterprisePersonServiceImpl implements IEnterprisePersonService {

    private final EnterprisePersonMapper enterprisePersonMapper;

    /**
     * 查询EnterprisePerson
     *
     * @param id 主键
     * @return EnterprisePerson
     */
    @Override
    public EnterprisePersonVo queryById(Long id) {
        return enterprisePersonMapper.selectVoById(id);
    }

    /**
     * 分页查询EnterprisePerson列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EnterprisePerson分页列表
     */
    @Override
    public PageResult<EnterprisePersonVo> queryPageList(EnterprisePersonBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EnterprisePerson> lqw = buildQueryWrapper(bo);
        Page<EnterprisePersonVo> result = enterprisePersonMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EnterprisePerson列表
     *
     * @param bo 查询条件
     * @return EnterprisePerson列表
     */
    @Override
    public List<EnterprisePersonVo> queryList(EnterprisePersonBo bo) {
        LambdaQueryWrapper<EnterprisePerson> lqw = buildQueryWrapper(bo);
        return enterprisePersonMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EnterprisePerson> buildQueryWrapper(EnterprisePersonBo bo) {
        return QueryBuilder.lambda(EnterprisePerson.class)
            .eqIfText(EnterprisePerson::getEmpCode, bo.getEmpCode())
            .eqIfText(EnterprisePerson::getEmpName, bo.getEmpName())
            .eqIfText(EnterprisePerson::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(EnterprisePerson::getOrgCode, bo.getOrgCode())
            .eqIfText(EnterprisePerson::getStatus, bo.getStatus())
            .orderByAsc(EnterprisePerson::getId)
            .build();
    }

    /**
     * 新增EnterprisePerson
     *
     * @param bo EnterprisePerson
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EnterprisePersonBo bo) {
        EnterprisePerson add = MapstructUtils.convert(bo, EnterprisePerson.class);
        validEntityBeforeSave(add);
        boolean flag = enterprisePersonMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EnterprisePerson
     *
     * @param bo EnterprisePerson
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EnterprisePersonBo bo) {
        EnterprisePerson update = MapstructUtils.convert(bo, EnterprisePerson.class);
        validEntityBeforeSave(update);
        return enterprisePersonMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EnterprisePerson entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EnterprisePerson信息
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
        return enterprisePersonMapper.deleteByIds(ids) > 0;
    }

}
