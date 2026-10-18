package org.dromara.system.service;

import org.dromara.system.domain.vo.LawsVo;
import org.dromara.system.domain.bo.LawsBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * LawsService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
public interface ILawsService {

    /**
     * 查询Laws
     *
     * @param id 主键
     * @return Laws
     */
    LawsVo queryById(Long id);

    /**
     * 分页查询Laws列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Laws分页列表
     */
    PageResult<LawsVo> queryPageList(LawsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Laws列表
     *
     * @param bo 查询条件
     * @return Laws列表
     */
    List<LawsVo> queryList(LawsBo bo);


    /**
     * 新增Laws
     *
     * @param bo Laws
     * @return 是否新增成功
     */
    Boolean insertByBo(LawsBo bo);

    /**
     * 修改Laws
     *
     * @param bo Laws
     * @return 是否修改成功
     */
    Boolean updateByBo(LawsBo bo);



    /**
     * 校验并批量删除Laws信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
