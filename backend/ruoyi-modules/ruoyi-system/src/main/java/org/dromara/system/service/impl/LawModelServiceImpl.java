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
import org.dromara.system.domain.bo.LawModelBo;
import org.dromara.system.domain.vo.LawModelVo;
import org.dromara.system.domain.LawModel;
import org.dromara.system.mapper.LawModelMapper;
import org.dromara.system.service.ILawModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * LawModelService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LawModelServiceImpl implements ILawModelService {

    private final LawModelMapper lawModelMapper;

    /**
     * 查询LawModel
     *
     * @param id 主键
     * @return LawModel
     */
    @Override
    public LawModelVo queryById(Long id) {
        return lawModelMapper.selectVoById(id);
    }

    /**
     * 分页查询LawModel列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return LawModel分页列表
     */
    @Override
    public PageResult<LawModelVo> queryPageList(LawModelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<LawModel> lqw = buildQueryWrapper(bo);
        Page<LawModelVo> result = lawModelMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的LawModel列表
     *
     * @param bo 查询条件
     * @return LawModel列表
     */
    @Override
    public List<LawModelVo> queryList(LawModelBo bo) {
        LambdaQueryWrapper<LawModel> lqw = buildQueryWrapper(bo);
        return lawModelMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<LawModel> buildQueryWrapper(LawModelBo bo) {
        return QueryBuilder.lambda(LawModel.class)
            .eqIfText(LawModel::getTargetCode, bo.getTargetCode())
            .eqIfText(LawModel::getSecendModuleName, bo.getSecendModuleName())
            .eqIfText(LawModel::getStatus, bo.getStatus())
            .orderByAsc(LawModel::getId)
            .build();
    }

    /**
     * 新增LawModel
     *
     * @param bo LawModel
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(LawModelBo bo) {
        LawModel add = MapstructUtils.convert(bo, LawModel.class);
        validEntityBeforeSave(add);
        boolean flag = lawModelMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改LawModel
     *
     * @param bo LawModel
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(LawModelBo bo) {
        LawModel update = MapstructUtils.convert(bo, LawModel.class);
        validEntityBeforeSave(update);
        return lawModelMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(LawModel entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除LawModel信息
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
        return lawModelMapper.deleteByIds(ids) > 0;
    }

}
