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
import org.dromara.system.domain.bo.ExamQuestionBo;
import org.dromara.system.domain.vo.ExamQuestionVo;
import org.dromara.system.domain.ExamQuestion;
import org.dromara.system.mapper.ExamQuestionMapper;
import org.dromara.system.service.IExamQuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * ExamQuestionService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExamQuestionServiceImpl implements IExamQuestionService {

    private final ExamQuestionMapper examQuestionMapper;

    /**
     * 查询ExamQuestion
     *
     * @param id 主键
     * @return ExamQuestion
     */
    @Override
    public ExamQuestionVo queryById(Long id) {
        return examQuestionMapper.selectVoById(id);
    }

    /**
     * 分页查询ExamQuestion列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ExamQuestion分页列表
     */
    @Override
    public PageResult<ExamQuestionVo> queryPageList(ExamQuestionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ExamQuestion> lqw = buildQueryWrapper(bo);
        Page<ExamQuestionVo> result = examQuestionMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的ExamQuestion列表
     *
     * @param bo 查询条件
     * @return ExamQuestion列表
     */
    @Override
    public List<ExamQuestionVo> queryList(ExamQuestionBo bo) {
        LambdaQueryWrapper<ExamQuestion> lqw = buildQueryWrapper(bo);
        return examQuestionMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<ExamQuestion> buildQueryWrapper(ExamQuestionBo bo) {
        return QueryBuilder.lambda(ExamQuestion.class)
            .eqIfText(ExamQuestion::getStatus, bo.getStatus())
            .orderByAsc(ExamQuestion::getId)
            .build();
    }

    /**
     * 新增ExamQuestion
     *
     * @param bo ExamQuestion
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ExamQuestionBo bo) {
        ExamQuestion add = MapstructUtils.convert(bo, ExamQuestion.class);
        validEntityBeforeSave(add);
        boolean flag = examQuestionMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改ExamQuestion
     *
     * @param bo ExamQuestion
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ExamQuestionBo bo) {
        ExamQuestion update = MapstructUtils.convert(bo, ExamQuestion.class);
        validEntityBeforeSave(update);
        return examQuestionMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ExamQuestion entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除ExamQuestion信息
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
        return examQuestionMapper.deleteByIds(ids) > 0;
    }

}
