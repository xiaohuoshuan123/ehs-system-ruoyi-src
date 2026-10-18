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
import org.dromara.system.domain.bo.StockBo;
import org.dromara.system.domain.vo.StockVo;
import org.dromara.system.domain.Stock;
import org.dromara.system.mapper.StockMapper;
import org.dromara.system.service.IStockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * StockService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockMapper stockMapper;

    /**
     * 查询Stock
     *
     * @param id 主键
     * @return Stock
     */
    @Override
    public StockVo queryById(Long id) {
        return stockMapper.selectVoById(id);
    }

    /**
     * 分页查询Stock列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Stock分页列表
     */
    @Override
    public PageResult<StockVo> queryPageList(StockBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Stock> lqw = buildQueryWrapper(bo);
        Page<StockVo> result = stockMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Stock列表
     *
     * @param bo 查询条件
     * @return Stock列表
     */
    @Override
    public List<StockVo> queryList(StockBo bo) {
        LambdaQueryWrapper<Stock> lqw = buildQueryWrapper(bo);
        return stockMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Stock> buildQueryWrapper(StockBo bo) {
        return QueryBuilder.lambda(Stock.class)
            .eqIfText(Stock::getStorageName, bo.getStorageName())
            .eqIfText(Stock::getArticleName, bo.getArticleName())
            .eqIfText(Stock::getArticleCode, bo.getArticleCode())
            .eqIfText(Stock::getStatus, bo.getStatus())
            .orderByAsc(Stock::getId)
            .build();
    }

    /**
     * 新增Stock
     *
     * @param bo Stock
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StockBo bo) {
        Stock add = MapstructUtils.convert(bo, Stock.class);
        validEntityBeforeSave(add);
        boolean flag = stockMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Stock
     *
     * @param bo Stock
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StockBo bo) {
        Stock update = MapstructUtils.convert(bo, Stock.class);
        validEntityBeforeSave(update);
        return stockMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Stock entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Stock信息
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
        return stockMapper.deleteByIds(ids) > 0;
    }

}
