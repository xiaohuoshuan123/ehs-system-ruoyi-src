package org.dromara.system.service;

import org.dromara.system.domain.vo.PlanVo;
import org.dromara.system.domain.bo.PlanBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * PlanService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
public interface IPlanService {

    /**
     * 查询Plan
     *
     * @param id 主键
     * @return Plan
     */
    PlanVo queryById(Long id);

    /**
     * 分页查询Plan列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Plan分页列表
     */
    PageResult<PlanVo> queryPageList(PlanBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Plan列表
     *
     * @param bo 查询条件
     * @return Plan列表
     */
    List<PlanVo> queryList(PlanBo bo);


    /**
     * 新增Plan
     *
     * @param bo Plan
     * @return 是否新增成功
     */
    Boolean insertByBo(PlanBo bo);

    /**
     * 修改Plan
     *
     * @param bo Plan
     * @return 是否修改成功
     */
    Boolean updateByBo(PlanBo bo);



    /**
     * 校验并批量删除Plan信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
