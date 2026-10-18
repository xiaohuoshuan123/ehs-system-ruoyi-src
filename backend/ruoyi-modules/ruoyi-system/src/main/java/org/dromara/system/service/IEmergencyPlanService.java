package org.dromara.system.service;

import org.dromara.system.domain.vo.EmergencyPlanVo;
import org.dromara.system.domain.bo.EmergencyPlanBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EmergencyPlanService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IEmergencyPlanService {

    /**
     * 查询EmergencyPlan
     *
     * @param id 主键
     * @return EmergencyPlan
     */
    EmergencyPlanVo queryById(Long id);

    /**
     * 分页查询EmergencyPlan列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyPlan分页列表
     */
    PageResult<EmergencyPlanVo> queryPageList(EmergencyPlanBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EmergencyPlan列表
     *
     * @param bo 查询条件
     * @return EmergencyPlan列表
     */
    List<EmergencyPlanVo> queryList(EmergencyPlanBo bo);


    /**
     * 新增EmergencyPlan
     *
     * @param bo EmergencyPlan
     * @return 是否新增成功
     */
    Boolean insertByBo(EmergencyPlanBo bo);

    /**
     * 修改EmergencyPlan
     *
     * @param bo EmergencyPlan
     * @return 是否修改成功
     */
    Boolean updateByBo(EmergencyPlanBo bo);



    /**
     * 校验并批量删除EmergencyPlan信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
