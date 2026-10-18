package org.dromara.system.service;

import org.dromara.system.domain.vo.EmergencyMaterialVo;
import org.dromara.system.domain.bo.EmergencyMaterialBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EmergencyMaterialService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IEmergencyMaterialService {

    /**
     * 查询EmergencyMaterial
     *
     * @param id 主键
     * @return EmergencyMaterial
     */
    EmergencyMaterialVo queryById(Long id);

    /**
     * 分页查询EmergencyMaterial列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyMaterial分页列表
     */
    PageResult<EmergencyMaterialVo> queryPageList(EmergencyMaterialBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EmergencyMaterial列表
     *
     * @param bo 查询条件
     * @return EmergencyMaterial列表
     */
    List<EmergencyMaterialVo> queryList(EmergencyMaterialBo bo);


    /**
     * 新增EmergencyMaterial
     *
     * @param bo EmergencyMaterial
     * @return 是否新增成功
     */
    Boolean insertByBo(EmergencyMaterialBo bo);

    /**
     * 修改EmergencyMaterial
     *
     * @param bo EmergencyMaterial
     * @return 是否修改成功
     */
    Boolean updateByBo(EmergencyMaterialBo bo);



    /**
     * 校验并批量删除EmergencyMaterial信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
