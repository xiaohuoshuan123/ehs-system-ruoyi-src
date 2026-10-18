package org.dromara.system.service;

import org.dromara.system.domain.vo.SafetyDutyVo;
import org.dromara.system.domain.bo.SafetyDutyBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SafetyDutyService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
public interface ISafetyDutyService {

    /**
     * 查询SafetyDuty
     *
     * @param id 主键
     * @return SafetyDuty
     */
    SafetyDutyVo queryById(Long id);

    /**
     * 分页查询SafetyDuty列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyDuty分页列表
     */
    PageResult<SafetyDutyVo> queryPageList(SafetyDutyBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SafetyDuty列表
     *
     * @param bo 查询条件
     * @return SafetyDuty列表
     */
    List<SafetyDutyVo> queryList(SafetyDutyBo bo);


    /**
     * 新增SafetyDuty
     *
     * @param bo SafetyDuty
     * @return 是否新增成功
     */
    Boolean insertByBo(SafetyDutyBo bo);

    /**
     * 修改SafetyDuty
     *
     * @param bo SafetyDuty
     * @return 是否修改成功
     */
    Boolean updateByBo(SafetyDutyBo bo);



    /**
     * 校验并批量删除SafetyDuty信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
