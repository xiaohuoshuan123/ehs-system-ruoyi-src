package org.dromara.system.service;

import org.dromara.system.domain.vo.ExamPaperVo;
import org.dromara.system.domain.bo.ExamPaperBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * ExamPaperService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface IExamPaperService {

    /**
     * 查询ExamPaper
     *
     * @param id 主键
     * @return ExamPaper
     */
    ExamPaperVo queryById(Long id);

    /**
     * 分页查询ExamPaper列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ExamPaper分页列表
     */
    PageResult<ExamPaperVo> queryPageList(ExamPaperBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的ExamPaper列表
     *
     * @param bo 查询条件
     * @return ExamPaper列表
     */
    List<ExamPaperVo> queryList(ExamPaperBo bo);


    /**
     * 新增ExamPaper
     *
     * @param bo ExamPaper
     * @return 是否新增成功
     */
    Boolean insertByBo(ExamPaperBo bo);

    /**
     * 修改ExamPaper
     *
     * @param bo ExamPaper
     * @return 是否修改成功
     */
    Boolean updateByBo(ExamPaperBo bo);



    /**
     * 校验并批量删除ExamPaper信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
