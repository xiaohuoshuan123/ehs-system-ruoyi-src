package org.dromara.system.service;

import org.dromara.system.domain.vo.MsdsVo;
import org.dromara.system.domain.bo.MsdsBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * MsdsService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface IMsdsService {

    /**
     * 查询Msds
     *
     * @param id 主键
     * @return Msds
     */
    MsdsVo queryById(Long id);

    /**
     * 分页查询Msds列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Msds分页列表
     */
    PageResult<MsdsVo> queryPageList(MsdsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Msds列表
     *
     * @param bo 查询条件
     * @return Msds列表
     */
    List<MsdsVo> queryList(MsdsBo bo);


    /**
     * 新增Msds
     *
     * @param bo Msds
     * @return 是否新增成功
     */
    Boolean insertByBo(MsdsBo bo);

    /**
     * 修改Msds
     *
     * @param bo Msds
     * @return 是否修改成功
     */
    Boolean updateByBo(MsdsBo bo);



    /**
     * 校验并批量删除Msds信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
