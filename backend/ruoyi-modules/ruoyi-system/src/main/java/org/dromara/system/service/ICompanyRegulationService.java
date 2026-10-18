package org.dromara.system.service;

import org.dromara.system.domain.vo.CompanyRegulationVo;
import org.dromara.system.domain.bo.CompanyRegulationBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * CompanyRegulationService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
public interface ICompanyRegulationService {

    /**
     * 查询CompanyRegulation
     *
     * @param id 主键
     * @return CompanyRegulation
     */
    CompanyRegulationVo queryById(Long id);

    /**
     * 分页查询CompanyRegulation列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return CompanyRegulation分页列表
     */
    PageResult<CompanyRegulationVo> queryPageList(CompanyRegulationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的CompanyRegulation列表
     *
     * @param bo 查询条件
     * @return CompanyRegulation列表
     */
    List<CompanyRegulationVo> queryList(CompanyRegulationBo bo);


    /**
     * 新增CompanyRegulation
     *
     * @param bo CompanyRegulation
     * @return 是否新增成功
     */
    Boolean insertByBo(CompanyRegulationBo bo);

    /**
     * 修改CompanyRegulation
     *
     * @param bo CompanyRegulation
     * @return 是否修改成功
     */
    Boolean updateByBo(CompanyRegulationBo bo);



    /**
     * 校验并批量删除CompanyRegulation信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
