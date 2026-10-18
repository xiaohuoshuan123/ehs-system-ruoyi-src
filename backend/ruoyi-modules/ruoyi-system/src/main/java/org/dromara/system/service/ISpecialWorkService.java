package org.dromara.system.service;

import org.dromara.system.domain.vo.SpecialWorkVo;
import org.dromara.system.domain.bo.SpecialWorkBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SpecialWorkService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
public interface ISpecialWorkService {

    /**
     * 查询SpecialWork
     *
     * @param id 主键
     * @return SpecialWork
     */
    SpecialWorkVo queryById(Long id);

    /**
     * 分页查询SpecialWork列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SpecialWork分页列表
     */
    PageResult<SpecialWorkVo> queryPageList(SpecialWorkBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SpecialWork列表
     *
     * @param bo 查询条件
     * @return SpecialWork列表
     */
    List<SpecialWorkVo> queryList(SpecialWorkBo bo);


    /**
     * 新增SpecialWork
     *
     * @param bo SpecialWork
     * @return 是否新增成功
     */
    Boolean insertByBo(SpecialWorkBo bo);

    /**
     * 修改SpecialWork
     *
     * @param bo SpecialWork
     * @return 是否修改成功
     */
    Boolean updateByBo(SpecialWorkBo bo);



    /**
     * 校验并批量删除SpecialWork信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
