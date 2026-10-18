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
import org.dromara.system.domain.bo.ExamPaperBo;
import org.dromara.system.domain.vo.ExamPaperVo;
import org.dromara.system.domain.ExamPaper;
import org.dromara.system.mapper.ExamPaperMapper;
import org.dromara.system.service.IExamPaperService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * ExamPaperService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExamPaperServiceImpl implements IExamPaperService {

    private final ExamPaperMapper examPaperMapper;

    /**
     * 查询ExamPaper
     *
     * @param id 主键
     * @return ExamPaper
     */
    @Override
    public ExamPaperVo queryById(Long id) {
        return examPaperMapper.selectVoById(id);
    }

    /**
     * 分页查询ExamPaper列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return ExamPaper分页列表
     */
    @Override
    public PageResult<ExamPaperVo> queryPageList(ExamPaperBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ExamPaper> lqw = buildQueryWrapper(bo);
        Page<ExamPaperVo> result = examPaperMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的ExamPaper列表
     *
     * @param bo 查询条件
     * @return ExamPaper列表
     */
    @Override
    public List<ExamPaperVo> queryList(ExamPaperBo bo) {
        LambdaQueryWrapper<ExamPaper> lqw = buildQueryWrapper(bo);
        return examPaperMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<ExamPaper> buildQueryWrapper(ExamPaperBo bo) {
        return QueryBuilder.lambda(ExamPaper.class)
            .eqIfText(ExamPaper::getExamName, bo.getExamName())
            .eqIfText(ExamPaper::getStatus, bo.getStatus())
            .orderByAsc(ExamPaper::getId)
            .build();
    }

    /**
     * 新增ExamPaper
     *
     * @param bo ExamPaper
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ExamPaperBo bo) {
        ExamPaper add = MapstructUtils.convert(bo, ExamPaper.class);
        validEntityBeforeSave(add);
        boolean flag = examPaperMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改ExamPaper
     *
     * @param bo ExamPaper
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ExamPaperBo bo) {
        ExamPaper update = MapstructUtils.convert(bo, ExamPaper.class);
        validEntityBeforeSave(update);
        return examPaperMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ExamPaper entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除ExamPaper信息
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
        return examPaperMapper.deleteByIds(ids) > 0;
    }

}
