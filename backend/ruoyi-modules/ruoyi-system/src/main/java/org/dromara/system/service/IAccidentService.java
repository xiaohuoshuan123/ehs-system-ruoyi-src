package org.dromara.system.service;

import org.dromara.system.domain.vo.AccidentVo;
import org.dromara.system.domain.bo.AccidentBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * AccidentService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IAccidentService {

    /**
     * 查询Accident
     *
     * @param id 主键
     * @return Accident
     */
    AccidentVo queryById(Long id);

    /**
     * 分页查询Accident列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Accident分页列表
     */
    PageResult<AccidentVo> queryPageList(AccidentBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Accident列表
     *
     * @param bo 查询条件
     * @return Accident列表
     */
    List<AccidentVo> queryList(AccidentBo bo);


    /**
     * 新增Accident
     *
     * @param bo Accident
     * @return 是否新增成功
     */
    Boolean insertByBo(AccidentBo bo);

    /**
     * 修改Accident
     *
     * @param bo Accident
     * @return 是否修改成功
     */
    Boolean updateByBo(AccidentBo bo);



    /**
     * 校验并批量删除Accident信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
