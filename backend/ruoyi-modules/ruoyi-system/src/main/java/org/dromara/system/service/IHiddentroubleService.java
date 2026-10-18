package org.dromara.system.service;

import org.dromara.system.domain.vo.HiddentroubleVo;
import org.dromara.system.domain.bo.HiddentroubleBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * HiddentroubleService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
public interface IHiddentroubleService {

    /**
     * 查询Hiddentrouble
     *
     * @param id 主键
     * @return Hiddentrouble
     */
    HiddentroubleVo queryById(Long id);

    /**
     * 分页查询Hiddentrouble列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Hiddentrouble分页列表
     */
    PageResult<HiddentroubleVo> queryPageList(HiddentroubleBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Hiddentrouble列表
     *
     * @param bo 查询条件
     * @return Hiddentrouble列表
     */
    List<HiddentroubleVo> queryList(HiddentroubleBo bo);


    /**
     * 新增Hiddentrouble
     *
     * @param bo Hiddentrouble
     * @return 是否新增成功
     */
    Boolean insertByBo(HiddentroubleBo bo);

    /**
     * 修改Hiddentrouble
     *
     * @param bo Hiddentrouble
     * @return 是否修改成功
     */
    Boolean updateByBo(HiddentroubleBo bo);



    /**
     * 校验并批量删除Hiddentrouble信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 提交隐患（暂存0→提交1）
     */
    Boolean submitHiddentrouble(Long id);

    /**
     * 整改隐患（提交1→整改2）
     */
    Boolean rectifyHiddentrouble(Long id);

    /**
     * 验证隐患（整改2→验证3）
     */
    Boolean verifyHiddentrouble(Long id);

    /**
     * 关闭隐患（验证3→关闭4）
     */
    Boolean closeHiddentrouble(Long id);

    /**
     * 撤回隐患（提交1→撤回5）
     */
    Boolean revokeHiddentrouble(Long id);

}
