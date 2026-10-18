package org.dromara.system.service;

import org.dromara.system.domain.vo.StorageVo;
import org.dromara.system.domain.bo.StorageBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * StorageService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
public interface IStorageService {

    /**
     * 查询Storage
     *
     * @param id 主键
     * @return Storage
     */
    StorageVo queryById(Long id);

    /**
     * 分页查询Storage列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Storage分页列表
     */
    PageResult<StorageVo> queryPageList(StorageBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Storage列表
     *
     * @param bo 查询条件
     * @return Storage列表
     */
    List<StorageVo> queryList(StorageBo bo);


    /**
     * 新增Storage
     *
     * @param bo Storage
     * @return 是否新增成功
     */
    Boolean insertByBo(StorageBo bo);

    /**
     * 修改Storage
     *
     * @param bo Storage
     * @return 是否修改成功
     */
    Boolean updateByBo(StorageBo bo);



    /**
     * 校验并批量删除Storage信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
