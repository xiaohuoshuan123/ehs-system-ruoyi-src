package org.dromara.system.service;

import org.dromara.system.domain.vo.StandardDeptVo;
import org.dromara.system.domain.bo.StandardDeptBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * StandardDeptService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface IStandardDeptService {

    /**
     * 查询StandardDept
     *
     * @param id 主键
     * @return StandardDept
     */
    StandardDeptVo queryById(Long id);

    /**
     * 分页查询StandardDept列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return StandardDept分页列表
     */
    PageResult<StandardDeptVo> queryPageList(StandardDeptBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的StandardDept列表
     *
     * @param bo 查询条件
     * @return StandardDept列表
     */
    List<StandardDeptVo> queryList(StandardDeptBo bo);


    /**
     * 新增StandardDept
     *
     * @param bo StandardDept
     * @return 是否新增成功
     */
    Boolean insertByBo(StandardDeptBo bo);

    /**
     * 修改StandardDept
     *
     * @param bo StandardDept
     * @return 是否修改成功
     */
    Boolean updateByBo(StandardDeptBo bo);



    /**
     * 校验并批量删除StandardDept信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
