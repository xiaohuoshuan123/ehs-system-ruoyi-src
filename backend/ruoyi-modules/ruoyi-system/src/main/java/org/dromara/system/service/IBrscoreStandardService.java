package org.dromara.system.service;

import org.dromara.system.domain.vo.BrscoreStandardVo;
import org.dromara.system.domain.bo.BrscoreStandardBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * BrscoreStandardService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface IBrscoreStandardService {

    /**
     * 查询BrscoreStandard
     *
     * @param id 主键
     * @return BrscoreStandard
     */
    BrscoreStandardVo queryById(Long id);

    /**
     * 分页查询BrscoreStandard列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return BrscoreStandard分页列表
     */
    PageResult<BrscoreStandardVo> queryPageList(BrscoreStandardBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的BrscoreStandard列表
     *
     * @param bo 查询条件
     * @return BrscoreStandard列表
     */
    List<BrscoreStandardVo> queryList(BrscoreStandardBo bo);


    /**
     * 新增BrscoreStandard
     *
     * @param bo BrscoreStandard
     * @return 是否新增成功
     */
    Boolean insertByBo(BrscoreStandardBo bo);

    /**
     * 修改BrscoreStandard
     *
     * @param bo BrscoreStandard
     * @return 是否修改成功
     */
    Boolean updateByBo(BrscoreStandardBo bo);



    /**
     * 校验并批量删除BrscoreStandard信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
