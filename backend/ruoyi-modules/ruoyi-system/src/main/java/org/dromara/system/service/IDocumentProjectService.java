package org.dromara.system.service;

import org.dromara.system.domain.vo.DocumentProjectVo;
import org.dromara.system.domain.bo.DocumentProjectBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * DocumentProjectService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
public interface IDocumentProjectService {

    /**
     * 查询DocumentProject
     *
     * @param id 主键
     * @return DocumentProject
     */
    DocumentProjectVo queryById(Long id);

    /**
     * 分页查询DocumentProject列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return DocumentProject分页列表
     */
    PageResult<DocumentProjectVo> queryPageList(DocumentProjectBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的DocumentProject列表
     *
     * @param bo 查询条件
     * @return DocumentProject列表
     */
    List<DocumentProjectVo> queryList(DocumentProjectBo bo);


    /**
     * 新增DocumentProject
     *
     * @param bo DocumentProject
     * @return 是否新增成功
     */
    Boolean insertByBo(DocumentProjectBo bo);

    /**
     * 修改DocumentProject
     *
     * @param bo DocumentProject
     * @return 是否修改成功
     */
    Boolean updateByBo(DocumentProjectBo bo);



    /**
     * 校验并批量删除DocumentProject信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
