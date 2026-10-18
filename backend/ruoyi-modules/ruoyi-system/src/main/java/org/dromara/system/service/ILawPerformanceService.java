package org.dromara.system.service;

import org.dromara.system.domain.vo.LawPerformanceVo;
import org.dromara.system.domain.bo.LawPerformanceBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * LawPerformanceService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
public interface ILawPerformanceService {

    /**
     * 查询LawPerformance
     *
     * @param id 主键
     * @return LawPerformance
     */
    LawPerformanceVo queryById(Long id);

    /**
     * 分页查询LawPerformance列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return LawPerformance分页列表
     */
    PageResult<LawPerformanceVo> queryPageList(LawPerformanceBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的LawPerformance列表
     *
     * @param bo 查询条件
     * @return LawPerformance列表
     */
    List<LawPerformanceVo> queryList(LawPerformanceBo bo);


    /**
     * 新增LawPerformance
     *
     * @param bo LawPerformance
     * @return 是否新增成功
     */
    Boolean insertByBo(LawPerformanceBo bo);

    /**
     * 修改LawPerformance
     *
     * @param bo LawPerformance
     * @return 是否修改成功
     */
    Boolean updateByBo(LawPerformanceBo bo);



    /**
     * 校验并批量删除LawPerformance信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
