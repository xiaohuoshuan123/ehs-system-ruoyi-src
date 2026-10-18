package org.dromara.system.service;

import org.dromara.system.domain.vo.SignTemplateVo;
import org.dromara.system.domain.bo.SignTemplateBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SignTemplateService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface ISignTemplateService {

    /**
     * 查询SignTemplate
     *
     * @param id 主键
     * @return SignTemplate
     */
    SignTemplateVo queryById(Long id);

    /**
     * 分页查询SignTemplate列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SignTemplate分页列表
     */
    PageResult<SignTemplateVo> queryPageList(SignTemplateBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SignTemplate列表
     *
     * @param bo 查询条件
     * @return SignTemplate列表
     */
    List<SignTemplateVo> queryList(SignTemplateBo bo);


    /**
     * 新增SignTemplate
     *
     * @param bo SignTemplate
     * @return 是否新增成功
     */
    Boolean insertByBo(SignTemplateBo bo);

    /**
     * 修改SignTemplate
     *
     * @param bo SignTemplate
     * @return 是否修改成功
     */
    Boolean updateByBo(SignTemplateBo bo);



    /**
     * 校验并批量删除SignTemplate信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
