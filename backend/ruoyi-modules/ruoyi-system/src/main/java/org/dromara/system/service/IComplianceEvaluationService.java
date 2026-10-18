package org.dromara.system.service;

import org.dromara.system.domain.vo.ComplianceEvaluationVo;
import org.dromara.system.domain.bo.ComplianceEvaluationBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * ComplianceEvaluationService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface IComplianceEvaluationService {

    /**
     * 查询ComplianceEvaluation
     *
     * @param id 主键
     * @return ComplianceEvaluation
     */
    ComplianceEvaluationVo queryById(Long id);

    /**
     * 分页查询ComplianceEvaluation列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ComplianceEvaluation分页列表
     */
    PageResult<ComplianceEvaluationVo> queryPageList(ComplianceEvaluationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的ComplianceEvaluation列表
     *
     * @param bo 查询条件
     * @return ComplianceEvaluation列表
     */
    List<ComplianceEvaluationVo> queryList(ComplianceEvaluationBo bo);


    /**
     * 新增ComplianceEvaluation
     *
     * @param bo ComplianceEvaluation
     * @return 是否新增成功
     */
    Boolean insertByBo(ComplianceEvaluationBo bo);

    /**
     * 修改ComplianceEvaluation
     *
     * @param bo ComplianceEvaluation
     * @return 是否修改成功
     */
    Boolean updateByBo(ComplianceEvaluationBo bo);



    /**
     * 校验并批量删除ComplianceEvaluation信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
