package org.dromara.system.service;

import org.dromara.system.domain.vo.HazardousMaterialVo;
import org.dromara.system.domain.bo.HazardousMaterialBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * HazardousMaterialService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface IHazardousMaterialService {

    /**
     * 查询HazardousMaterial
     *
     * @param id 主键
     * @return HazardousMaterial
     */
    HazardousMaterialVo queryById(Long id);

    /**
     * 分页查询HazardousMaterial列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return HazardousMaterial分页列表
     */
    PageResult<HazardousMaterialVo> queryPageList(HazardousMaterialBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的HazardousMaterial列表
     *
     * @param bo 查询条件
     * @return HazardousMaterial列表
     */
    List<HazardousMaterialVo> queryList(HazardousMaterialBo bo);


    /**
     * 新增HazardousMaterial
     *
     * @param bo HazardousMaterial
     * @return 是否新增成功
     */
    Boolean insertByBo(HazardousMaterialBo bo);

    /**
     * 修改HazardousMaterial
     *
     * @param bo HazardousMaterial
     * @return 是否修改成功
     */
    Boolean updateByBo(HazardousMaterialBo bo);



    /**
     * 校验并批量删除HazardousMaterial信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
