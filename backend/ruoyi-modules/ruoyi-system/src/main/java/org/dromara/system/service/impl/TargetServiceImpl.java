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
import org.dromara.system.domain.bo.TargetBo;
import org.dromara.system.domain.vo.TargetVo;
import org.dromara.system.domain.Target;
import org.dromara.system.mapper.TargetMapper;
import org.dromara.system.service.ITargetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * TargetService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TargetServiceImpl implements ITargetService {

    private final TargetMapper targetMapper;

    /**
     * 查询Target
     *
     * @param id 主键
     * @return Target
     */
    @Override
    public TargetVo queryById(Long id) {
        return targetMapper.selectVoById(id);
    }

    /**
     * 分页查询Target列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Target分页列表
     */
    @Override
    public PageResult<TargetVo> queryPageList(TargetBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Target> lqw = buildQueryWrapper(bo);
        Page<TargetVo> result = targetMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Target列表
     *
     * @param bo 查询条件
     * @return Target列表
     */
    @Override
    public List<TargetVo> queryList(TargetBo bo) {
        LambdaQueryWrapper<Target> lqw = buildQueryWrapper(bo);
        return targetMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Target> buildQueryWrapper(TargetBo bo) {
        return QueryBuilder.lambda(Target.class)
            .eqIfText(Target::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(Target::getOrgCode, bo.getOrgCode())
            .eqIfText(Target::getTargetName, bo.getTargetName())
            .eqIfText(Target::getStatus, bo.getStatus())
            .orderByAsc(Target::getId)
            .build();
    }

    /**
     * 新增Target
     *
     * @param bo Target
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TargetBo bo) {
        Target add = MapstructUtils.convert(bo, Target.class);
        validEntityBeforeSave(add);
        boolean flag = targetMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Target
     *
     * @param bo Target
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TargetBo bo) {
        Target update = MapstructUtils.convert(bo, Target.class);
        validEntityBeforeSave(update);
        return targetMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Target entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Target信息
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
        return targetMapper.deleteByIds(ids) > 0;
    }

}
