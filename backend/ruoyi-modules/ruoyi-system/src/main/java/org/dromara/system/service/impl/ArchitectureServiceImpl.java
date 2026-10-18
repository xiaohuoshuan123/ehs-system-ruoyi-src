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
import org.dromara.system.domain.bo.ArchitectureBo;
import org.dromara.system.domain.vo.ArchitectureVo;
import org.dromara.system.domain.Architecture;
import org.dromara.system.mapper.ArchitectureMapper;
import org.dromara.system.service.IArchitectureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * ArchitectureService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArchitectureServiceImpl implements IArchitectureService {

    private final ArchitectureMapper architectureMapper;

    /**
     * 查询Architecture
     *
     * @param id 主键
     * @return Architecture
     */
    @Override
    public ArchitectureVo queryById(Long id) {
        return architectureMapper.selectVoById(id);
    }

    /**
     * 分页查询Architecture列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Architecture分页列表
     */
    @Override
    public PageResult<ArchitectureVo> queryPageList(ArchitectureBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Architecture> lqw = buildQueryWrapper(bo);
        Page<ArchitectureVo> result = architectureMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Architecture列表
     *
     * @param bo 查询条件
     * @return Architecture列表
     */
    @Override
    public List<ArchitectureVo> queryList(ArchitectureBo bo) {
        LambdaQueryWrapper<Architecture> lqw = buildQueryWrapper(bo);
        return architectureMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Architecture> buildQueryWrapper(ArchitectureBo bo) {
        return QueryBuilder.lambda(Architecture.class)
            .eqIfText(Architecture::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(Architecture::getOrgCode, bo.getOrgCode())
            .eqIfText(Architecture::getOrgName, bo.getOrgName())
            .eqIfText(Architecture::getParentCode, bo.getParentCode())
            .eqIfText(Architecture::getStatus, bo.getStatus())
            .orderByAsc(Architecture::getId)
            .build();
    }

    /**
     * 新增Architecture
     *
     * @param bo Architecture
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ArchitectureBo bo) {
        Architecture add = MapstructUtils.convert(bo, Architecture.class);
        validEntityBeforeSave(add);
        boolean flag = architectureMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Architecture
     *
     * @param bo Architecture
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ArchitectureBo bo) {
        Architecture update = MapstructUtils.convert(bo, Architecture.class);
        validEntityBeforeSave(update);
        return architectureMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Architecture entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Architecture信息
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
        return architectureMapper.deleteByIds(ids) > 0;
    }

}
