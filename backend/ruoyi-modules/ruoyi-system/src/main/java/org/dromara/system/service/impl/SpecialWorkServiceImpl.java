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
import org.dromara.system.domain.bo.SpecialWorkBo;
import org.dromara.system.domain.vo.SpecialWorkVo;
import org.dromara.system.domain.SpecialWork;
import org.dromara.system.mapper.SpecialWorkMapper;
import org.dromara.system.service.ISpecialWorkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SpecialWorkService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SpecialWorkServiceImpl implements ISpecialWorkService {

    private final SpecialWorkMapper specialWorkMapper;

    /**
     * 查询SpecialWork
     *
     * @param id 主键
     * @return SpecialWork
     */
    @Override
    public SpecialWorkVo queryById(Long id) {
        return specialWorkMapper.selectVoById(id);
    }

    /**
     * 分页查询SpecialWork列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SpecialWork分页列表
     */
    @Override
    public PageResult<SpecialWorkVo> queryPageList(SpecialWorkBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SpecialWork> lqw = buildQueryWrapper(bo);
        Page<SpecialWorkVo> result = specialWorkMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SpecialWork列表
     *
     * @param bo 查询条件
     * @return SpecialWork列表
     */
    @Override
    public List<SpecialWorkVo> queryList(SpecialWorkBo bo) {
        LambdaQueryWrapper<SpecialWork> lqw = buildQueryWrapper(bo);
        return specialWorkMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SpecialWork> buildQueryWrapper(SpecialWorkBo bo) {
        return QueryBuilder.lambda(SpecialWork.class)
            .eqIfText(SpecialWork::getEmpCode, bo.getEmpCode())
            .eqIfText(SpecialWork::getEmpName, bo.getEmpName())
            .eqIfText(SpecialWork::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(SpecialWork::getStatus, bo.getStatus())
            .orderByAsc(SpecialWork::getId)
            .build();
    }

    /**
     * 新增SpecialWork
     *
     * @param bo SpecialWork
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SpecialWorkBo bo) {
        SpecialWork add = MapstructUtils.convert(bo, SpecialWork.class);
        validEntityBeforeSave(add);
        boolean flag = specialWorkMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SpecialWork
     *
     * @param bo SpecialWork
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SpecialWorkBo bo) {
        SpecialWork update = MapstructUtils.convert(bo, SpecialWork.class);
        validEntityBeforeSave(update);
        return specialWorkMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SpecialWork entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SpecialWork信息
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
        return specialWorkMapper.deleteByIds(ids) > 0;
    }

}
