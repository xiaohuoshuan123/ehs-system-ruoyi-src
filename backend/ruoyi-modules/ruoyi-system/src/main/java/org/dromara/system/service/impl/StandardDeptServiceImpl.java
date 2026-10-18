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
import org.dromara.system.domain.bo.StandardDeptBo;
import org.dromara.system.domain.vo.StandardDeptVo;
import org.dromara.system.domain.StandardDept;
import org.dromara.system.mapper.StandardDeptMapper;
import org.dromara.system.service.IStandardDeptService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * StandardDeptService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StandardDeptServiceImpl implements IStandardDeptService {

    private final StandardDeptMapper standardDeptMapper;

    /**
     * 查询StandardDept
     *
     * @param id 主键
     * @return StandardDept
     */
    @Override
    public StandardDeptVo queryById(Long id) {
        return standardDeptMapper.selectVoById(id);
    }

    /**
     * 分页查询StandardDept列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return StandardDept分页列表
     */
    @Override
    public PageResult<StandardDeptVo> queryPageList(StandardDeptBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<StandardDept> lqw = buildQueryWrapper(bo);
        Page<StandardDeptVo> result = standardDeptMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的StandardDept列表
     *
     * @param bo 查询条件
     * @return StandardDept列表
     */
    @Override
    public List<StandardDeptVo> queryList(StandardDeptBo bo) {
        LambdaQueryWrapper<StandardDept> lqw = buildQueryWrapper(bo);
        return standardDeptMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<StandardDept> buildQueryWrapper(StandardDeptBo bo) {
        return QueryBuilder.lambda(StandardDept.class)
            .eqIfText(StandardDept::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(StandardDept::getDeptName, bo.getDeptName())
            .eqIfText(StandardDept::getDeptCode, bo.getDeptCode())
            .eqIfText(StandardDept::getStatus, bo.getStatus())
            .orderByAsc(StandardDept::getId)
            .build();
    }

    /**
     * 新增StandardDept
     *
     * @param bo StandardDept
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StandardDeptBo bo) {
        StandardDept add = MapstructUtils.convert(bo, StandardDept.class);
        validEntityBeforeSave(add);
        boolean flag = standardDeptMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改StandardDept
     *
     * @param bo StandardDept
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StandardDeptBo bo) {
        StandardDept update = MapstructUtils.convert(bo, StandardDept.class);
        validEntityBeforeSave(update);
        return standardDeptMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(StandardDept entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除StandardDept信息
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
        return standardDeptMapper.deleteByIds(ids) > 0;
    }

}
