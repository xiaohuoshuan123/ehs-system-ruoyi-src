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
import org.dromara.system.domain.bo.PatrolTaskBo;
import org.dromara.system.domain.vo.PatrolTaskVo;
import org.dromara.system.domain.PatrolTask;
import org.dromara.system.mapper.PatrolTaskMapper;
import org.dromara.system.service.IPatrolTaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * PatrolTaskService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PatrolTaskServiceImpl implements IPatrolTaskService {

    private final PatrolTaskMapper patrolTaskMapper;

    /**
     * 查询PatrolTask
     *
     * @param id 主键
     * @return PatrolTask
     */
    @Override
    public PatrolTaskVo queryById(Long id) {
        return patrolTaskMapper.selectVoById(id);
    }

    /**
     * 分页查询PatrolTask列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return PatrolTask分页列表
     */
    @Override
    public PageResult<PatrolTaskVo> queryPageList(PatrolTaskBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PatrolTask> lqw = buildQueryWrapper(bo);
        Page<PatrolTaskVo> result = patrolTaskMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的PatrolTask列表
     *
     * @param bo 查询条件
     * @return PatrolTask列表
     */
    @Override
    public List<PatrolTaskVo> queryList(PatrolTaskBo bo) {
        LambdaQueryWrapper<PatrolTask> lqw = buildQueryWrapper(bo);
        return patrolTaskMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<PatrolTask> buildQueryWrapper(PatrolTaskBo bo) {
        return QueryBuilder.lambda(PatrolTask.class)
            .eqIfText(PatrolTask::getCheckOrgCodePath, bo.getCheckOrgCodePath())
            .eqIfText(PatrolTask::getTaskName, bo.getTaskName())
            .eqIfText(PatrolTask::getCheckerName, bo.getCheckerName())
            .eqIfText(PatrolTask::getStatus, bo.getStatus())
            .orderByAsc(PatrolTask::getId)
            .build();
    }

    /**
     * 新增PatrolTask
     *
     * @param bo PatrolTask
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(PatrolTaskBo bo) {
        PatrolTask add = MapstructUtils.convert(bo, PatrolTask.class);
        validEntityBeforeSave(add);
        boolean flag = patrolTaskMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改PatrolTask
     *
     * @param bo PatrolTask
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(PatrolTaskBo bo) {
        PatrolTask update = MapstructUtils.convert(bo, PatrolTask.class);
        validEntityBeforeSave(update);
        return patrolTaskMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PatrolTask entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除PatrolTask信息
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
        return patrolTaskMapper.deleteByIds(ids) > 0;
    }

}
