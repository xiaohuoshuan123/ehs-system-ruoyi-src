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
import org.dromara.system.domain.bo.EmergencyMaterialBo;
import org.dromara.system.domain.vo.EmergencyMaterialVo;
import org.dromara.system.domain.EmergencyMaterial;
import org.dromara.system.mapper.EmergencyMaterialMapper;
import org.dromara.system.service.IEmergencyMaterialService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EmergencyMaterialService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmergencyMaterialServiceImpl implements IEmergencyMaterialService {

    private final EmergencyMaterialMapper emergencyMaterialMapper;

    /**
     * 查询EmergencyMaterial
     *
     * @param id 主键
     * @return EmergencyMaterial
     */
    @Override
    public EmergencyMaterialVo queryById(Long id) {
        return emergencyMaterialMapper.selectVoById(id);
    }

    /**
     * 分页查询EmergencyMaterial列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EmergencyMaterial分页列表
     */
    @Override
    public PageResult<EmergencyMaterialVo> queryPageList(EmergencyMaterialBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EmergencyMaterial> lqw = buildQueryWrapper(bo);
        Page<EmergencyMaterialVo> result = emergencyMaterialMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EmergencyMaterial列表
     *
     * @param bo 查询条件
     * @return EmergencyMaterial列表
     */
    @Override
    public List<EmergencyMaterialVo> queryList(EmergencyMaterialBo bo) {
        LambdaQueryWrapper<EmergencyMaterial> lqw = buildQueryWrapper(bo);
        return emergencyMaterialMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EmergencyMaterial> buildQueryWrapper(EmergencyMaterialBo bo) {
        return QueryBuilder.lambda(EmergencyMaterial.class)
            .eqIfText(EmergencyMaterial::getOrgCodeId, bo.getOrgCodeId())
            .eqIfText(EmergencyMaterial::getMaterialName, bo.getMaterialName())
            .eqIfText(EmergencyMaterial::getStatus, bo.getStatus())
            .orderByAsc(EmergencyMaterial::getId)
            .build();
    }

    /**
     * 新增EmergencyMaterial
     *
     * @param bo EmergencyMaterial
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EmergencyMaterialBo bo) {
        EmergencyMaterial add = MapstructUtils.convert(bo, EmergencyMaterial.class);
        validEntityBeforeSave(add);
        boolean flag = emergencyMaterialMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EmergencyMaterial
     *
     * @param bo EmergencyMaterial
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EmergencyMaterialBo bo) {
        EmergencyMaterial update = MapstructUtils.convert(bo, EmergencyMaterial.class);
        validEntityBeforeSave(update);
        return emergencyMaterialMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EmergencyMaterial entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EmergencyMaterial信息
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
        return emergencyMaterialMapper.deleteByIds(ids) > 0;
    }

}
