package org.dromara.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.query.QueryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SafetyMeetingBo;
import org.dromara.system.domain.vo.SafetyMeetingVo;
import org.dromara.system.domain.SafetyMeeting;
import org.dromara.system.mapper.SafetyMeetingMapper;
import org.dromara.system.service.ISafetyMeetingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SafetyMeetingService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SafetyMeetingServiceImpl implements ISafetyMeetingService {

    private final SafetyMeetingMapper safetyMeetingMapper;

    /**
     * 查询SafetyMeeting
     *
     * @param id 主键
     * @return SafetyMeeting
     */
    @Override
    public SafetyMeetingVo queryById(Long id) {
        return safetyMeetingMapper.selectVoById(id);
    }

    /**
     * 分页查询SafetyMeeting列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyMeeting分页列表
     */
    @Override
    public PageResult<SafetyMeetingVo> queryPageList(SafetyMeetingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SafetyMeeting> lqw = buildQueryWrapper(bo);
        Page<SafetyMeetingVo> result = safetyMeetingMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SafetyMeeting列表
     *
     * @param bo 查询条件
     * @return SafetyMeeting列表
     */
    @Override
    public List<SafetyMeetingVo> queryList(SafetyMeetingBo bo) {
        LambdaQueryWrapper<SafetyMeeting> lqw = buildQueryWrapper(bo);
        return safetyMeetingMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SafetyMeeting> buildQueryWrapper(SafetyMeetingBo bo) {
        return QueryBuilder.lambda(SafetyMeeting.class)
            .eqIfText(SafetyMeeting::getOrgName, bo.getOrgName())
            .eqIfText(SafetyMeeting::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(SafetyMeeting::getStatus, bo.getStatus())
            .orderByAsc(SafetyMeeting::getId)
            .build();
    }

    /**
     * 新增SafetyMeeting
     *
     * @param bo SafetyMeeting
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SafetyMeetingBo bo) {
        SafetyMeeting add = MapstructUtils.convert(bo, SafetyMeeting.class);
        validEntityBeforeSave(add);
        boolean flag = safetyMeetingMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SafetyMeeting
     *
     * @param bo SafetyMeeting
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SafetyMeetingBo bo) {
        SafetyMeeting update = MapstructUtils.convert(bo, SafetyMeeting.class);
        validEntityBeforeSave(update);
        return safetyMeetingMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SafetyMeeting entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SafetyMeeting信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 可在此扩展删除前业务校验
        }
        return safetyMeetingMapper.deleteByIds(ids) > 0;
    }

}
