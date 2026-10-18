package org.dromara.system.service;

import org.dromara.system.domain.vo.TargetVo;
import org.dromara.system.domain.bo.TargetBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * TargetService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
public interface ITargetService {

    /**
     * 查询Target
     *
     * @param id 主键
     * @return Target
     */
    TargetVo queryById(Long id);

    /**
     * 分页查询Target列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Target分页列表
     */
    PageResult<TargetVo> queryPageList(TargetBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Target列表
     *
     * @param bo 查询条件
     * @return Target列表
     */
    List<TargetVo> queryList(TargetBo bo);


    /**
     * 新增Target
     *
     * @param bo Target
     * @return 是否新增成功
     */
    Boolean insertByBo(TargetBo bo);

    /**
     * 修改Target
     *
     * @param bo Target
     * @return 是否修改成功
     */
    Boolean updateByBo(TargetBo bo);



    /**
     * 校验并批量删除Target信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
