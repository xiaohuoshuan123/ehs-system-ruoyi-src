package org.dromara.system.service;

import org.dromara.system.domain.vo.EducationTrainingRecordVo;
import org.dromara.system.domain.bo.EducationTrainingRecordBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EducationTrainingRecordService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
public interface IEducationTrainingRecordService {

    /**
     * 查询EducationTrainingRecord
     *
     * @param id 主键
     * @return EducationTrainingRecord
     */
    EducationTrainingRecordVo queryById(Long id);

    /**
     * 分页查询EducationTrainingRecord列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EducationTrainingRecord分页列表
     */
    PageResult<EducationTrainingRecordVo> queryPageList(EducationTrainingRecordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EducationTrainingRecord列表
     *
     * @param bo 查询条件
     * @return EducationTrainingRecord列表
     */
    List<EducationTrainingRecordVo> queryList(EducationTrainingRecordBo bo);


    /**
     * 新增EducationTrainingRecord
     *
     * @param bo EducationTrainingRecord
     * @return 是否新增成功
     */
    Boolean insertByBo(EducationTrainingRecordBo bo);

    /**
     * 修改EducationTrainingRecord
     *
     * @param bo EducationTrainingRecord
     * @return 是否修改成功
     */
    Boolean updateByBo(EducationTrainingRecordBo bo);



    /**
     * 校验并批量删除EducationTrainingRecord信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
