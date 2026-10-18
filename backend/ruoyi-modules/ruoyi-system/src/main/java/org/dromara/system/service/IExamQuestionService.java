package org.dromara.system.service;

import org.dromara.system.domain.vo.ExamQuestionVo;
import org.dromara.system.domain.bo.ExamQuestionBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * ExamQuestionService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface IExamQuestionService {

    /**
     * 查询ExamQuestion
     *
     * @param id 主键
     * @return ExamQuestion
     */
    ExamQuestionVo queryById(Long id);

    /**
     * 分页查询ExamQuestion列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ExamQuestion分页列表
     */
    PageResult<ExamQuestionVo> queryPageList(ExamQuestionBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的ExamQuestion列表
     *
     * @param bo 查询条件
     * @return ExamQuestion列表
     */
    List<ExamQuestionVo> queryList(ExamQuestionBo bo);


    /**
     * 新增ExamQuestion
     *
     * @param bo ExamQuestion
     * @return 是否新增成功
     */
    Boolean insertByBo(ExamQuestionBo bo);

    /**
     * 修改ExamQuestion
     *
     * @param bo ExamQuestion
     * @return 是否修改成功
     */
    Boolean updateByBo(ExamQuestionBo bo);



    /**
     * 校验并批量删除ExamQuestion信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
