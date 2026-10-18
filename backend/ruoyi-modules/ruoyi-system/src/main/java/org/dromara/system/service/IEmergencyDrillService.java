package org.dromara.system.service;

import org.dromara.system.domain.vo.EmergencyDrillVo;
import org.dromara.system.domain.bo.EmergencyDrillBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EmergencyDrillService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IEmergencyDrillService {

    /**
     * 查询EmergencyDrill
     *
     * @param id 主键
     * @return EmergencyDrill
     */
    EmergencyDrillVo queryById(Long id);

    /**
     * 分页查询EmergencyDrill列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyDrill分页列表
     */
    PageResult<EmergencyDrillVo> queryPageList(EmergencyDrillBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EmergencyDrill列表
     *
     * @param bo 查询条件
     * @return EmergencyDrill列表
     */
    List<EmergencyDrillVo> queryList(EmergencyDrillBo bo);


    /**
     * 新增EmergencyDrill
     *
     * @param bo EmergencyDrill
     * @return 是否新增成功
     */
    Boolean insertByBo(EmergencyDrillBo bo);

    /**
     * 修改EmergencyDrill
     *
     * @param bo EmergencyDrill
     * @return 是否修改成功
     */
    Boolean updateByBo(EmergencyDrillBo bo);



    /**
     * 校验并批量删除EmergencyDrill信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
