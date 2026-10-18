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
import org.dromara.system.domain.bo.LawsBo;
import org.dromara.system.domain.vo.LawsVo;
import org.dromara.system.domain.Laws;
import org.dromara.system.mapper.LawsMapper;
import org.dromara.system.service.ILawsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * LawsService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LawsServiceImpl implements ILawsService {

    private final LawsMapper lawsMapper;

    /**
     * 查询Laws
     *
     * @param id 主键
     * @return Laws
     */
    @Override
    public LawsVo queryById(Long id) {
        return lawsMapper.selectVoById(id);
    }

    /**
     * 分页查询Laws列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Laws分页列表
     */
    @Override
    public PageResult<LawsVo> queryPageList(LawsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Laws> lqw = buildQueryWrapper(bo);
        Page<LawsVo> result = lawsMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Laws列表
     *
     * @param bo 查询条件
     * @return Laws列表
     */
    @Override
    public List<LawsVo> queryList(LawsBo bo) {
        LambdaQueryWrapper<Laws> lqw = buildQueryWrapper(bo);
        return lawsMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Laws> buildQueryWrapper(LawsBo bo) {
        return QueryBuilder.lambda(Laws.class)
            .eqIfText(Laws::getFileName, bo.getFileName())
            .eqIfText(Laws::getOrgCode, bo.getOrgCode())
            .eqIfText(Laws::getOrgName, bo.getOrgName())
            .eqIfText(Laws::getStatus, bo.getStatus())
            .orderByAsc(Laws::getId)
            .build();
    }

    /**
     * 新增Laws
     *
     * @param bo Laws
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(LawsBo bo) {
        Laws add = MapstructUtils.convert(bo, Laws.class);
        validEntityBeforeSave(add);
        boolean flag = lawsMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Laws
     *
     * @param bo Laws
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(LawsBo bo) {
        Laws update = MapstructUtils.convert(bo, Laws.class);
        validEntityBeforeSave(update);
        return lawsMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Laws entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Laws信息
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
        return lawsMapper.deleteByIds(ids) > 0;
    }

}
