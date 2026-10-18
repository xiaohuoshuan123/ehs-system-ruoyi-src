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
import org.dromara.system.domain.bo.EducationTrainingRecordBo;
import org.dromara.system.domain.vo.EducationTrainingRecordVo;
import org.dromara.system.domain.EducationTrainingRecord;
import org.dromara.system.mapper.EducationTrainingRecordMapper;
import org.dromara.system.service.IEducationTrainingRecordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * EducationTrainingRecordService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EducationTrainingRecordServiceImpl implements IEducationTrainingRecordService {

    private final EducationTrainingRecordMapper educationTrainingRecordMapper;

    /**
     * 查询EducationTrainingRecord
     *
     * @param id 主键
     * @return EducationTrainingRecord
     */
    @Override
    public EducationTrainingRecordVo queryById(Long id) {
        return educationTrainingRecordMapper.selectVoById(id);
    }

    /**
     * 分页查询EducationTrainingRecord列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return EducationTrainingRecord分页列表
     */
    @Override
    public PageResult<EducationTrainingRecordVo> queryPageList(EducationTrainingRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EducationTrainingRecord> lqw = buildQueryWrapper(bo);
        Page<EducationTrainingRecordVo> result = educationTrainingRecordMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的EducationTrainingRecord列表
     *
     * @param bo 查询条件
     * @return EducationTrainingRecord列表
     */
    @Override
    public List<EducationTrainingRecordVo> queryList(EducationTrainingRecordBo bo) {
        LambdaQueryWrapper<EducationTrainingRecord> lqw = buildQueryWrapper(bo);
        return educationTrainingRecordMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<EducationTrainingRecord> buildQueryWrapper(EducationTrainingRecordBo bo) {
        return QueryBuilder.lambda(EducationTrainingRecord.class)
            .eqIfText(EducationTrainingRecord::getOrgCode1, bo.getOrgCode1())
            .eqIfText(EducationTrainingRecord::getEmpCode, bo.getEmpCode())
            .eqIfText(EducationTrainingRecord::getEmpName, bo.getEmpName())
            .eqIfText(EducationTrainingRecord::getStatus, bo.getStatus())
            .orderByAsc(EducationTrainingRecord::getId)
            .build();
    }

    /**
     * 新增EducationTrainingRecord
     *
     * @param bo EducationTrainingRecord
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EducationTrainingRecordBo bo) {
        EducationTrainingRecord add = MapstructUtils.convert(bo, EducationTrainingRecord.class);
        validEntityBeforeSave(add);
        boolean flag = educationTrainingRecordMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改EducationTrainingRecord
     *
     * @param bo EducationTrainingRecord
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EducationTrainingRecordBo bo) {
        EducationTrainingRecord update = MapstructUtils.convert(bo, EducationTrainingRecord.class);
        validEntityBeforeSave(update);
        return educationTrainingRecordMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EducationTrainingRecord entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除EducationTrainingRecord信息
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
        return educationTrainingRecordMapper.deleteByIds(ids) > 0;
    }

}
