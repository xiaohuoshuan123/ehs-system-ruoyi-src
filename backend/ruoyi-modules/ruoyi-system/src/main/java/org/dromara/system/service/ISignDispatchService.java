package org.dromara.system.service;

import org.dromara.system.domain.vo.SignDispatchVo;
import org.dromara.system.domain.bo.SignDispatchBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SignDispatchService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface ISignDispatchService {

    /**
     * 查询SignDispatch
     *
     * @param id 主键
     * @return SignDispatch
     */
    SignDispatchVo queryById(Long id);

    /**
     * 分页查询SignDispatch列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SignDispatch分页列表
     */
    PageResult<SignDispatchVo> queryPageList(SignDispatchBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SignDispatch列表
     *
     * @param bo 查询条件
     * @return SignDispatch列表
     */
    List<SignDispatchVo> queryList(SignDispatchBo bo);


    /**
     * 新增SignDispatch
     *
     * @param bo SignDispatch
     * @return 是否新增成功
     */
    Boolean insertByBo(SignDispatchBo bo);

    /**
     * 修改SignDispatch
     *
     * @param bo SignDispatch
     * @return 是否修改成功
     */
    Boolean updateByBo(SignDispatchBo bo);



    /**
     * 校验并批量删除SignDispatch信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
