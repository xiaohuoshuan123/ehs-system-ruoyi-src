package org.dromara.system.service;

import org.dromara.system.domain.vo.SafetyStudyDtlVo;
import org.dromara.system.domain.bo.SafetyStudyDtlBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SafetyStudyDtlService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface ISafetyStudyDtlService {

    /**
     * 查询SafetyStudyDtl
     *
     * @param id 主键
     * @return SafetyStudyDtl
     */
    SafetyStudyDtlVo queryById(Long id);

    /**
     * 分页查询SafetyStudyDtl列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyStudyDtl分页列表
     */
    PageResult<SafetyStudyDtlVo> queryPageList(SafetyStudyDtlBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SafetyStudyDtl列表
     *
     * @param bo 查询条件
     * @return SafetyStudyDtl列表
     */
    List<SafetyStudyDtlVo> queryList(SafetyStudyDtlBo bo);


    /**
     * 新增SafetyStudyDtl
     *
     * @param bo SafetyStudyDtl
     * @return 是否新增成功
     */
    Boolean insertByBo(SafetyStudyDtlBo bo);

    /**
     * 修改SafetyStudyDtl
     *
     * @param bo SafetyStudyDtl
     * @return 是否修改成功
     */
    Boolean updateByBo(SafetyStudyDtlBo bo);



    /**
     * 校验并批量删除SafetyStudyDtl信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
