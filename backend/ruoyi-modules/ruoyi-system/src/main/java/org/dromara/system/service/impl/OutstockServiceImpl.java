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
import org.dromara.system.domain.bo.OutstockBo;
import org.dromara.system.domain.vo.OutstockVo;
import org.dromara.system.domain.Outstock;
import org.dromara.system.mapper.OutstockMapper;
import org.dromara.system.service.IOutstockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * OutstockService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OutstockServiceImpl implements IOutstockService {

    private final OutstockMapper outstockMapper;

    /**
     * 查询Outstock
     *
     * @param id 主键
     * @return Outstock
     */
    @Override
    public OutstockVo queryById(Long id) {
        return outstockMapper.selectVoById(id);
    }

    /**
     * 分页查询Outstock列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Outstock分页列表
     */
    @Override
    public PageResult<OutstockVo> queryPageList(OutstockBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Outstock> lqw = buildQueryWrapper(bo);
        Page<OutstockVo> result = outstockMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Outstock列表
     *
     * @param bo 查询条件
     * @return Outstock列表
     */
    @Override
    public List<OutstockVo> queryList(OutstockBo bo) {
        LambdaQueryWrapper<Outstock> lqw = buildQueryWrapper(bo);
        return outstockMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Outstock> buildQueryWrapper(OutstockBo bo) {
        return QueryBuilder.lambda(Outstock.class)
            .eqIfText(Outstock::getArticleCode, bo.getArticleCode())
            .eqIfText(Outstock::getArticleName, bo.getArticleName())
            .eqIfText(Outstock::getStorageName, bo.getStorageName())
            .eqIfText(Outstock::getStatus, bo.getStatus())
            .orderByAsc(Outstock::getId)
            .build();
    }

    /**
     * 新增Outstock
     *
     * @param bo Outstock
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(OutstockBo bo) {
        Outstock add = MapstructUtils.convert(bo, Outstock.class);
        validEntityBeforeSave(add);
        boolean flag = outstockMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Outstock
     *
     * @param bo Outstock
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(OutstockBo bo) {
        Outstock update = MapstructUtils.convert(bo, Outstock.class);
        validEntityBeforeSave(update);
        return outstockMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Outstock entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Outstock信息
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
        return outstockMapper.deleteByIds(ids) > 0;
    }

}
