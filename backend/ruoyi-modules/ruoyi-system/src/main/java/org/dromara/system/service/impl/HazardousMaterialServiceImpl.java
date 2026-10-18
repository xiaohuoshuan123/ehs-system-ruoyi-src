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
import org.dromara.system.domain.bo.HazardousMaterialBo;
import org.dromara.system.domain.vo.HazardousMaterialVo;
import org.dromara.system.domain.HazardousMaterial;
import org.dromara.system.mapper.HazardousMaterialMapper;
import org.dromara.system.service.IHazardousMaterialService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * HazardousMaterialService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class HazardousMaterialServiceImpl implements IHazardousMaterialService {

    private final HazardousMaterialMapper hazardousMaterialMapper;

    /**
     * 查询HazardousMaterial
     *
     * @param id 主键
     * @return HazardousMaterial
     */
    @Override
    public HazardousMaterialVo queryById(Long id) {
        return hazardousMaterialMapper.selectVoById(id);
    }

    /**
     * 分页查询HazardousMaterial列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return HazardousMaterial分页列表
     */
    @Override
    public PageResult<HazardousMaterialVo> queryPageList(HazardousMaterialBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HazardousMaterial> lqw = buildQueryWrapper(bo);
        Page<HazardousMaterialVo> result = hazardousMaterialMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的HazardousMaterial列表
     *
     * @param bo 查询条件
     * @return HazardousMaterial列表
     */
    @Override
    public List<HazardousMaterialVo> queryList(HazardousMaterialBo bo) {
        LambdaQueryWrapper<HazardousMaterial> lqw = buildQueryWrapper(bo);
        return hazardousMaterialMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<HazardousMaterial> buildQueryWrapper(HazardousMaterialBo bo) {
        return QueryBuilder.lambda(HazardousMaterial.class)
            .eqIfText(HazardousMaterial::getArticleCode, bo.getArticleCode())
            .eqIfText(HazardousMaterial::getArticleName, bo.getArticleName())
            .eqIfText(HazardousMaterial::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(HazardousMaterial::getStatus, bo.getStatus())
            .orderByAsc(HazardousMaterial::getId)
            .build();
    }

    /**
     * 新增HazardousMaterial
     *
     * @param bo HazardousMaterial
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(HazardousMaterialBo bo) {
        HazardousMaterial add = MapstructUtils.convert(bo, HazardousMaterial.class);
        validEntityBeforeSave(add);
        boolean flag = hazardousMaterialMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改HazardousMaterial
     *
     * @param bo HazardousMaterial
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(HazardousMaterialBo bo) {
        HazardousMaterial update = MapstructUtils.convert(bo, HazardousMaterial.class);
        validEntityBeforeSave(update);
        return hazardousMaterialMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HazardousMaterial entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除HazardousMaterial信息
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
        return hazardousMaterialMapper.deleteByIds(ids) > 0;
    }

}
