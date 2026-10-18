package org.dromara.system.service;

import org.dromara.system.domain.vo.SafetyMeetingVo;
import org.dromara.system.domain.bo.SafetyMeetingBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SafetyMeetingService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
public interface ISafetyMeetingService {

    /**
     * 查询SafetyMeeting
     *
     * @param id 主键
     * @return SafetyMeeting
     */
    SafetyMeetingVo queryById(Long id);

    /**
     * 分页查询SafetyMeeting列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyMeeting分页列表
     */
    PageResult<SafetyMeetingVo> queryPageList(SafetyMeetingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SafetyMeeting列表
     *
     * @param bo 查询条件
     * @return SafetyMeeting列表
     */
    List<SafetyMeetingVo> queryList(SafetyMeetingBo bo);


    /**
     * 新增SafetyMeeting
     *
     * @param bo SafetyMeeting
     * @return 是否新增成功
     */
    Boolean insertByBo(SafetyMeetingBo bo);

    /**
     * 修改SafetyMeeting
     *
     * @param bo SafetyMeeting
     * @return 是否修改成功
     */
    Boolean updateByBo(SafetyMeetingBo bo);



    /**
     * 校验并批量删除SafetyMeeting信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
