package org.dromara.system.service;

import org.dromara.system.domain.vo.EducationTrainingMstVo;
import org.dromara.system.domain.bo.EducationTrainingMstBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EducationTrainingMstService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface IEducationTrainingMstService {

    /**
     * 查询EducationTrainingMst
     *
     * @param id 主键
     * @return EducationTrainingMst
     */
    EducationTrainingMstVo queryById(Long id);

    /**
     * 分页查询EducationTrainingMst列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EducationTrainingMst分页列表
     */
    PageResult<EducationTrainingMstVo> queryPageList(EducationTrainingMstBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EducationTrainingMst列表
     *
     * @param bo 查询条件
     * @return EducationTrainingMst列表
     */
    List<EducationTrainingMstVo> queryList(EducationTrainingMstBo bo);


    /**
     * 新增EducationTrainingMst
     *
     * @param bo EducationTrainingMst
     * @return 是否新增成功
     */
    Boolean insertByBo(EducationTrainingMstBo bo);

    /**
     * 修改EducationTrainingMst
     *
     * @param bo EducationTrainingMst
     * @return 是否修改成功
     */
    Boolean updateByBo(EducationTrainingMstBo bo);



    /**
     * 校验并批量删除EducationTrainingMst信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
