package org.dromara.system.service;

import org.dromara.system.domain.vo.KeyPositionVo;
import org.dromara.system.domain.bo.KeyPositionBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * KeyPositionService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IKeyPositionService {

    /**
     * 查询KeyPosition
     *
     * @param id 主键
     * @return KeyPosition
     */
    KeyPositionVo queryById(Long id);

    /**
     * 分页查询KeyPosition列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return KeyPosition分页列表
     */
    PageResult<KeyPositionVo> queryPageList(KeyPositionBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的KeyPosition列表
     *
     * @param bo 查询条件
     * @return KeyPosition列表
     */
    List<KeyPositionVo> queryList(KeyPositionBo bo);


    /**
     * 新增KeyPosition
     *
     * @param bo KeyPosition
     * @return 是否新增成功
     */
    Boolean insertByBo(KeyPositionBo bo);

    /**
     * 修改KeyPosition
     *
     * @param bo KeyPosition
     * @return 是否修改成功
     */
    Boolean updateByBo(KeyPositionBo bo);



    /**
     * 校验并批量删除KeyPosition信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
