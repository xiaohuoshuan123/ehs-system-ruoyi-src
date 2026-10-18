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
import org.dromara.system.domain.bo.SafetyStudyDtlBo;
import org.dromara.system.domain.vo.SafetyStudyDtlVo;
import org.dromara.system.domain.SafetyStudyDtl;
import org.dromara.system.mapper.SafetyStudyDtlMapper;
import org.dromara.system.service.ISafetyStudyDtlService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SafetyStudyDtlService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SafetyStudyDtlServiceImpl implements ISafetyStudyDtlService {

    private final SafetyStudyDtlMapper safetyStudyDtlMapper;

    /**
     * 查询SafetyStudyDtl
     *
     * @param id 主键
     * @return SafetyStudyDtl
     */
    @Override
    public SafetyStudyDtlVo queryById(Long id) {
        return safetyStudyDtlMapper.selectVoById(id);
    }

    /**
     * 分页查询SafetyStudyDtl列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyStudyDtl分页列表
     */
    @Override
    public PageResult<SafetyStudyDtlVo> queryPageList(SafetyStudyDtlBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SafetyStudyDtl> lqw = buildQueryWrapper(bo);
        Page<SafetyStudyDtlVo> result = safetyStudyDtlMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SafetyStudyDtl列表
     *
     * @param bo 查询条件
     * @return SafetyStudyDtl列表
     */
    @Override
    public List<SafetyStudyDtlVo> queryList(SafetyStudyDtlBo bo) {
        LambdaQueryWrapper<SafetyStudyDtl> lqw = buildQueryWrapper(bo);
        return safetyStudyDtlMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SafetyStudyDtl> buildQueryWrapper(SafetyStudyDtlBo bo) {
        return QueryBuilder.lambda(SafetyStudyDtl.class)
            .eqIfText(SafetyStudyDtl::getEmpCode, bo.getEmpCode())
            .eqIfText(SafetyStudyDtl::getEmpName, bo.getEmpName())
            .orderByAsc(SafetyStudyDtl::getId)
            .build();
    }

    /**
     * 新增SafetyStudyDtl
     *
     * @param bo SafetyStudyDtl
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SafetyStudyDtlBo bo) {
        SafetyStudyDtl add = MapstructUtils.convert(bo, SafetyStudyDtl.class);
        validEntityBeforeSave(add);
        boolean flag = safetyStudyDtlMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SafetyStudyDtl
     *
     * @param bo SafetyStudyDtl
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SafetyStudyDtlBo bo) {
        SafetyStudyDtl update = MapstructUtils.convert(bo, SafetyStudyDtl.class);
        validEntityBeforeSave(update);
        return safetyStudyDtlMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SafetyStudyDtl entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SafetyStudyDtl信息
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
        return safetyStudyDtlMapper.deleteByIds(ids) > 0;
    }

}
