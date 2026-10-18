package org.dromara.system.service;

import org.dromara.system.domain.vo.PatrolTaskVo;
import org.dromara.system.domain.bo.PatrolTaskBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * PatrolTaskService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IPatrolTaskService {

    /**
     * 查询PatrolTask
     *
     * @param id 主键
     * @return PatrolTask
     */
    PatrolTaskVo queryById(Long id);

    /**
     * 分页查询PatrolTask列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return PatrolTask分页列表
     */
    PageResult<PatrolTaskVo> queryPageList(PatrolTaskBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的PatrolTask列表
     *
     * @param bo 查询条件
     * @return PatrolTask列表
     */
    List<PatrolTaskVo> queryList(PatrolTaskBo bo);


    /**
     * 新增PatrolTask
     *
     * @param bo PatrolTask
     * @return 是否新增成功
     */
    Boolean insertByBo(PatrolTaskBo bo);

    /**
     * 修改PatrolTask
     *
     * @param bo PatrolTask
     * @return 是否修改成功
     */
    Boolean updateByBo(PatrolTaskBo bo);



    /**
     * 校验并批量删除PatrolTask信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
