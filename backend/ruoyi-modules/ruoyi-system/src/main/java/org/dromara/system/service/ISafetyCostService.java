package org.dromara.system.service;

import org.dromara.system.domain.vo.SafetyCostVo;
import org.dromara.system.domain.bo.SafetyCostBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SafetyCostService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface ISafetyCostService {

    /**
     * 查询SafetyCost
     *
     * @param id 主键
     * @return SafetyCost
     */
    SafetyCostVo queryById(Long id);

    /**
     * 分页查询SafetyCost列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyCost分页列表
     */
    PageResult<SafetyCostVo> queryPageList(SafetyCostBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SafetyCost列表
     *
     * @param bo 查询条件
     * @return SafetyCost列表
     */
    List<SafetyCostVo> queryList(SafetyCostBo bo);


    /**
     * 新增SafetyCost
     *
     * @param bo SafetyCost
     * @return 是否新增成功
     */
    Boolean insertByBo(SafetyCostBo bo);

    /**
     * 修改SafetyCost
     *
     * @param bo SafetyCost
     * @return 是否修改成功
     */
    Boolean updateByBo(SafetyCostBo bo);



    /**
     * 校验并批量删除SafetyCost信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
