package org.dromara.system.service;

import org.dromara.system.domain.vo.EmergencyPointVo;
import org.dromara.system.domain.bo.EmergencyPointBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EmergencyPointService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IEmergencyPointService {

    /**
     * 查询EmergencyPoint
     *
     * @param id 主键
     * @return EmergencyPoint
     */
    EmergencyPointVo queryById(Long id);

    /**
     * 分页查询EmergencyPoint列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyPoint分页列表
     */
    PageResult<EmergencyPointVo> queryPageList(EmergencyPointBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EmergencyPoint列表
     *
     * @param bo 查询条件
     * @return EmergencyPoint列表
     */
    List<EmergencyPointVo> queryList(EmergencyPointBo bo);


    /**
     * 新增EmergencyPoint
     *
     * @param bo EmergencyPoint
     * @return 是否新增成功
     */
    Boolean insertByBo(EmergencyPointBo bo);

    /**
     * 修改EmergencyPoint
     *
     * @param bo EmergencyPoint
     * @return 是否修改成功
     */
    Boolean updateByBo(EmergencyPointBo bo);



    /**
     * 校验并批量删除EmergencyPoint信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
