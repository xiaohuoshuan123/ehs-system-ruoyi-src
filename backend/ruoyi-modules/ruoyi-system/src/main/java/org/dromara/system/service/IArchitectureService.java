package org.dromara.system.service;

import org.dromara.system.domain.vo.ArchitectureVo;
import org.dromara.system.domain.bo.ArchitectureBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * ArchitectureService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface IArchitectureService {

    /**
     * 查询Architecture
     *
     * @param id 主键
     * @return Architecture
     */
    ArchitectureVo queryById(Long id);

    /**
     * 分页查询Architecture列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Architecture分页列表
     */
    PageResult<ArchitectureVo> queryPageList(ArchitectureBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Architecture列表
     *
     * @param bo 查询条件
     * @return Architecture列表
     */
    List<ArchitectureVo> queryList(ArchitectureBo bo);


    /**
     * 新增Architecture
     *
     * @param bo Architecture
     * @return 是否新增成功
     */
    Boolean insertByBo(ArchitectureBo bo);

    /**
     * 修改Architecture
     *
     * @param bo Architecture
     * @return 是否修改成功
     */
    Boolean updateByBo(ArchitectureBo bo);



    /**
     * 校验并批量删除Architecture信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
