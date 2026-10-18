package org.dromara.system.service;

import org.dromara.system.domain.vo.StockVo;
import org.dromara.system.domain.bo.StockBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * StockService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface IStockService {

    /**
     * 查询Stock
     *
     * @param id 主键
     * @return Stock
     */
    StockVo queryById(Long id);

    /**
     * 分页查询Stock列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Stock分页列表
     */
    PageResult<StockVo> queryPageList(StockBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Stock列表
     *
     * @param bo 查询条件
     * @return Stock列表
     */
    List<StockVo> queryList(StockBo bo);


    /**
     * 新增Stock
     *
     * @param bo Stock
     * @return 是否新增成功
     */
    Boolean insertByBo(StockBo bo);

    /**
     * 修改Stock
     *
     * @param bo Stock
     * @return 是否修改成功
     */
    Boolean updateByBo(StockBo bo);



    /**
     * 校验并批量删除Stock信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
