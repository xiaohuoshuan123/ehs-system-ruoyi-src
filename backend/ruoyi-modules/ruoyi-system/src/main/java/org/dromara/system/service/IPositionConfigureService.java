package org.dromara.system.service;

import org.dromara.system.domain.vo.PositionConfigureVo;
import org.dromara.system.domain.bo.PositionConfigureBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * PositionConfigureService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IPositionConfigureService {

    /**
     * 查询PositionConfigure
     *
     * @param id 主键
     * @return PositionConfigure
     */
    PositionConfigureVo queryById(Long id);

    /**
     * 分页查询PositionConfigure列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return PositionConfigure分页列表
     */
    PageResult<PositionConfigureVo> queryPageList(PositionConfigureBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的PositionConfigure列表
     *
     * @param bo 查询条件
     * @return PositionConfigure列表
     */
    List<PositionConfigureVo> queryList(PositionConfigureBo bo);


    /**
     * 新增PositionConfigure
     *
     * @param bo PositionConfigure
     * @return 是否新增成功
     */
    Boolean insertByBo(PositionConfigureBo bo);

    /**
     * 修改PositionConfigure
     *
     * @param bo PositionConfigure
     * @return 是否修改成功
     */
    Boolean updateByBo(PositionConfigureBo bo);



    /**
     * 校验并批量删除PositionConfigure信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
