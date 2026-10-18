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
import org.dromara.system.domain.bo.InstockBo;
import org.dromara.system.domain.vo.InstockVo;
import org.dromara.system.domain.Instock;
import org.dromara.system.mapper.InstockMapper;
import org.dromara.system.service.IInstockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * InstockService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class InstockServiceImpl implements IInstockService {

    private final InstockMapper instockMapper;

    /**
     * 查询Instock
     *
     * @param id 主键
     * @return Instock
     */
    @Override
    public InstockVo queryById(Long id) {
        return instockMapper.selectVoById(id);
    }

    /**
     * 分页查询Instock列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Instock分页列表
     */
    @Override
    public PageResult<InstockVo> queryPageList(InstockBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Instock> lqw = buildQueryWrapper(bo);
        Page<InstockVo> result = instockMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Instock列表
     *
     * @param bo 查询条件
     * @return Instock列表
     */
    @Override
    public List<InstockVo> queryList(InstockBo bo) {
        LambdaQueryWrapper<Instock> lqw = buildQueryWrapper(bo);
        return instockMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Instock> buildQueryWrapper(InstockBo bo) {
        return QueryBuilder.lambda(Instock.class)
            .eqIfText(Instock::getArticleCode, bo.getArticleCode())
            .eqIfText(Instock::getArticleName, bo.getArticleName())
            .eqIfText(Instock::getStorageName, bo.getStorageName())
            .eqIfText(Instock::getStatus, bo.getStatus())
            .orderByAsc(Instock::getId)
            .build();
    }

    /**
     * 新增Instock
     *
     * @param bo Instock
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(InstockBo bo) {
        Instock add = MapstructUtils.convert(bo, Instock.class);
        validEntityBeforeSave(add);
        boolean flag = instockMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Instock
     *
     * @param bo Instock
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(InstockBo bo) {
        Instock update = MapstructUtils.convert(bo, Instock.class);
        validEntityBeforeSave(update);
        return instockMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Instock entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Instock信息
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
        return instockMapper.deleteByIds(ids) > 0;
    }

}
