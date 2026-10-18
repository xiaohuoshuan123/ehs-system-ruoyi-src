package org.dromara.system.service;

import org.dromara.system.domain.vo.SafetyStudyMstVo;
import org.dromara.system.domain.bo.SafetyStudyMstBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SafetyStudyMstService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface ISafetyStudyMstService {

    /**
     * 查询SafetyStudyMst
     *
     * @param id 主键
     * @return SafetyStudyMst
     */
    SafetyStudyMstVo queryById(Long id);

    /**
     * 分页查询SafetyStudyMst列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyStudyMst分页列表
     */
    PageResult<SafetyStudyMstVo> queryPageList(SafetyStudyMstBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SafetyStudyMst列表
     *
     * @param bo 查询条件
     * @return SafetyStudyMst列表
     */
    List<SafetyStudyMstVo> queryList(SafetyStudyMstBo bo);


    /**
     * 新增SafetyStudyMst
     *
     * @param bo SafetyStudyMst
     * @return 是否新增成功
     */
    Boolean insertByBo(SafetyStudyMstBo bo);

    /**
     * 修改SafetyStudyMst
     *
     * @param bo SafetyStudyMst
     * @return 是否修改成功
     */
    Boolean updateByBo(SafetyStudyMstBo bo);



    /**
     * 校验并批量删除SafetyStudyMst信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
