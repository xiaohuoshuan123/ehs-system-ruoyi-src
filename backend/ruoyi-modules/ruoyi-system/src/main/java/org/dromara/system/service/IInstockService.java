package org.dromara.system.service;

import org.dromara.system.domain.vo.InstockVo;
import org.dromara.system.domain.bo.InstockBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * InstockService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface IInstockService {

    /**
     * 查询Instock
     *
     * @param id 主键
     * @return Instock
     */
    InstockVo queryById(Long id);

    /**
     * 分页查询Instock列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Instock分页列表
     */
    PageResult<InstockVo> queryPageList(InstockBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Instock列表
     *
     * @param bo 查询条件
     * @return Instock列表
     */
    List<InstockVo> queryList(InstockBo bo);


    /**
     * 新增Instock
     *
     * @param bo Instock
     * @return 是否新增成功
     */
    Boolean insertByBo(InstockBo bo);

    /**
     * 修改Instock
     *
     * @param bo Instock
     * @return 是否修改成功
     */
    Boolean updateByBo(InstockBo bo);



    /**
     * 校验并批量删除Instock信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
