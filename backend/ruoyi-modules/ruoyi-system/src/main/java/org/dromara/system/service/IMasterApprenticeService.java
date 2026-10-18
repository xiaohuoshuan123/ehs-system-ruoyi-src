package org.dromara.system.service;

import org.dromara.system.domain.vo.MasterApprenticeVo;
import org.dromara.system.domain.bo.MasterApprenticeBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * MasterApprenticeService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
public interface IMasterApprenticeService {

    /**
     * 查询MasterApprentice
     *
     * @param id 主键
     * @return MasterApprentice
     */
    MasterApprenticeVo queryById(Long id);

    /**
     * 分页查询MasterApprentice列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return MasterApprentice分页列表
     */
    PageResult<MasterApprenticeVo> queryPageList(MasterApprenticeBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的MasterApprentice列表
     *
     * @param bo 查询条件
     * @return MasterApprentice列表
     */
    List<MasterApprenticeVo> queryList(MasterApprenticeBo bo);


    /**
     * 新增MasterApprentice
     *
     * @param bo MasterApprentice
     * @return 是否新增成功
     */
    Boolean insertByBo(MasterApprenticeBo bo);

    /**
     * 修改MasterApprentice
     *
     * @param bo MasterApprentice
     * @return 是否修改成功
     */
    Boolean updateByBo(MasterApprenticeBo bo);



    /**
     * 校验并批量删除MasterApprentice信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
