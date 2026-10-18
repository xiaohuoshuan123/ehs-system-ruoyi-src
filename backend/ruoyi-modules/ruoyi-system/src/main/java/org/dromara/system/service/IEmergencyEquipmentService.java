package org.dromara.system.service;

import org.dromara.system.domain.vo.EmergencyEquipmentVo;
import org.dromara.system.domain.bo.EmergencyEquipmentBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EmergencyEquipmentService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IEmergencyEquipmentService {

    /**
     * 查询EmergencyEquipment
     *
     * @param id 主键
     * @return EmergencyEquipment
     */
    EmergencyEquipmentVo queryById(Long id);

    /**
     * 分页查询EmergencyEquipment列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyEquipment分页列表
     */
    PageResult<EmergencyEquipmentVo> queryPageList(EmergencyEquipmentBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EmergencyEquipment列表
     *
     * @param bo 查询条件
     * @return EmergencyEquipment列表
     */
    List<EmergencyEquipmentVo> queryList(EmergencyEquipmentBo bo);


    /**
     * 新增EmergencyEquipment
     *
     * @param bo EmergencyEquipment
     * @return 是否新增成功
     */
    Boolean insertByBo(EmergencyEquipmentBo bo);

    /**
     * 修改EmergencyEquipment
     *
     * @param bo EmergencyEquipment
     * @return 是否修改成功
     */
    Boolean updateByBo(EmergencyEquipmentBo bo);



    /**
     * 校验并批量删除EmergencyEquipment信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
