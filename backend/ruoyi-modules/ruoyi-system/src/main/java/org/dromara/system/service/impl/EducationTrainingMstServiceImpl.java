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
import org.dromara.system.domain.bo.EducationTrainingMstBo;
import org.dromara.system.domain.vo.EducationTrainingMstVo;
import org.dromara.system.domain.EducationTrainingMst;
import org.dromara.system.mapper.EducationTrainingMstMapper;
import org.dromara.system.service.IEducationTrainingMstService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EducationTrainingMstService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EducationTrainingMstServiceImpl implements IEducationTrainingMstService {

    private final EducationTrainingMstMapper educationTrainingMstMapper;

    /**
     * 查询EducationTrainingMst
     *
     * @param id 主键
     * @return EducationTrainingMst
     */
    @Override
    public EducationTrainingMstVo queryById(Long id) {
        return educationTrainingMstMapper.selectVoById(id);
    }

    /**
     * 分页查询EducationTrainingMst列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EducationTrainingMst分页列表
     */
    @Override
    public PageResult<EducationTrainingMstVo> queryPageList(EducationTrainingMstBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EducationTrainingMst> lqw = buildQueryWrapper(bo);
        Page<EducationTrainingMstVo> result = educationTrainingMstMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EducationTrainingMst列表
     *
     * @param bo 查询条件
     * @return EducationTrainingMst列表
     */
    @Override
    public List<EducationTrainingMstVo> queryList(EducationTrainingMstBo bo) {
        LambdaQueryWrapper<EducationTrainingMst> lqw = buildQueryWrapper(bo);
        return educationTrainingMstMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EducationTrainingMst> buildQueryWrapper(EducationTrainingMstBo bo) {
        return QueryBuilder.lambda(EducationTrainingMst.class)
            .eqIfText(EducationTrainingMst::getOrgCode, bo.getOrgCode())
            .eqIfText(EducationTrainingMst::getOrgCodePath, bo.getOrgCodePath())
            .eqIfText(EducationTrainingMst::getTrainName, bo.getTrainName())
            .eqIfText(EducationTrainingMst::getStatus, bo.getStatus())
            .orderByAsc(EducationTrainingMst::getId)
            .build();
    }

    /**
     * 新增EducationTrainingMst
     *
     * @param bo EducationTrainingMst
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EducationTrainingMstBo bo) {
        EducationTrainingMst add = MapstructUtils.convert(bo, EducationTrainingMst.class);
        validEntityBeforeSave(add);
        boolean flag = educationTrainingMstMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EducationTrainingMst
     *
     * @param bo EducationTrainingMst
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EducationTrainingMstBo bo) {
        EducationTrainingMst update = MapstructUtils.convert(bo, EducationTrainingMst.class);
        validEntityBeforeSave(update);
        return educationTrainingMstMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EducationTrainingMst entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EducationTrainingMst信息
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
        return educationTrainingMstMapper.deleteByIds(ids) > 0;
    }

}
