package org.dromara.system.service;

import org.dromara.system.domain.vo.EnterprisePersonVo;
import org.dromara.system.domain.bo.EnterprisePersonBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * EnterprisePersonService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
public interface IEnterprisePersonService {

    /**
     * 查询EnterprisePerson
     *
     * @param id 主键
     * @return EnterprisePerson
     */
    EnterprisePersonVo queryById(Long id);

    /**
     * 分页查询EnterprisePerson列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EnterprisePerson分页列表
     */
    PageResult<EnterprisePersonVo> queryPageList(EnterprisePersonBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的EnterprisePerson列表
     *
     * @param bo 查询条件
     * @return EnterprisePerson列表
     */
    List<EnterprisePersonVo> queryList(EnterprisePersonBo bo);


    /**
     * 新增EnterprisePerson
     *
     * @param bo EnterprisePerson
     * @return 是否新增成功
     */
    Boolean insertByBo(EnterprisePersonBo bo);

    /**
     * 修改EnterprisePerson
     *
     * @param bo EnterprisePerson
     * @return 是否修改成功
     */
    Boolean updateByBo(EnterprisePersonBo bo);



    /**
     * 校验并批量删除EnterprisePerson信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
