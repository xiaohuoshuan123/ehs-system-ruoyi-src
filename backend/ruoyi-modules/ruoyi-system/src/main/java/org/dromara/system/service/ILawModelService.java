package org.dromara.system.service;

import org.dromara.system.domain.vo.LawModelVo;
import org.dromara.system.domain.bo.LawModelBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * LawModelService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface ILawModelService {

    /**
     * 查询LawModel
     *
     * @param id 主键
     * @return LawModel
     */
    LawModelVo queryById(Long id);

    /**
     * 分页查询LawModel列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return LawModel分页列表
     */
    PageResult<LawModelVo> queryPageList(LawModelBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的LawModel列表
     *
     * @param bo 查询条件
     * @return LawModel列表
     */
    List<LawModelVo> queryList(LawModelBo bo);


    /**
     * 新增LawModel
     *
     * @param bo LawModel
     * @return 是否新增成功
     */
    Boolean insertByBo(LawModelBo bo);

    /**
     * 修改LawModel
     *
     * @param bo LawModel
     * @return 是否修改成功
     */
    Boolean updateByBo(LawModelBo bo);



    /**
     * 校验并批量删除LawModel信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
