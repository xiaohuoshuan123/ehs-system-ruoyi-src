package org.dromara.system.service;

import org.dromara.system.domain.vo.RiskControlListVo;
import org.dromara.system.domain.bo.RiskControlListBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * RiskControlListService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface IRiskControlListService {

    /**
     * 查询RiskControlList
     *
     * @param id 主键
     * @return RiskControlList
     */
    RiskControlListVo queryById(Long id);

    /**
     * 分页查询RiskControlList列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return RiskControlList分页列表
     */
    PageResult<RiskControlListVo> queryPageList(RiskControlListBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的RiskControlList列表
     *
     * @param bo 查询条件
     * @return RiskControlList列表
     */
    List<RiskControlListVo> queryList(RiskControlListBo bo);


    /**
     * 新增RiskControlList
     *
     * @param bo RiskControlList
     * @return 是否新增成功
     */
    Boolean insertByBo(RiskControlListBo bo);

    /**
     * 修改RiskControlList
     *
     * @param bo RiskControlList
     * @return 是否修改成功
     */
    Boolean updateByBo(RiskControlListBo bo);



    /**
     * 校验并批量删除RiskControlList信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
