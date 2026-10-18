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
import org.dromara.system.domain.bo.SignTemplateBo;
import org.dromara.system.domain.vo.SignTemplateVo;
import org.dromara.system.domain.SignTemplate;
import org.dromara.system.mapper.SignTemplateMapper;
import org.dromara.system.service.ISignTemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SignTemplateService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SignTemplateServiceImpl implements ISignTemplateService {

    private final SignTemplateMapper signTemplateMapper;

    /**
     * 查询SignTemplate
     *
     * @param id 主键
     * @return SignTemplate
     */
    @Override
    public SignTemplateVo queryById(Long id) {
        return signTemplateMapper.selectVoById(id);
    }

    /**
     * 分页查询SignTemplate列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SignTemplate分页列表
     */
    @Override
    public PageResult<SignTemplateVo> queryPageList(SignTemplateBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SignTemplate> lqw = buildQueryWrapper(bo);
        Page<SignTemplateVo> result = signTemplateMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SignTemplate列表
     *
     * @param bo 查询条件
     * @return SignTemplate列表
     */
    @Override
    public List<SignTemplateVo> queryList(SignTemplateBo bo) {
        LambdaQueryWrapper<SignTemplate> lqw = buildQueryWrapper(bo);
        return signTemplateMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SignTemplate> buildQueryWrapper(SignTemplateBo bo) {
        return QueryBuilder.lambda(SignTemplate.class)
            .eqIfText(SignTemplate::getTemplateName, bo.getTemplateName())
            .eqIfText(SignTemplate::getStatus, bo.getStatus())
            .orderByAsc(SignTemplate::getId)
            .build();
    }

    /**
     * 新增SignTemplate
     *
     * @param bo SignTemplate
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SignTemplateBo bo) {
        SignTemplate add = MapstructUtils.convert(bo, SignTemplate.class);
        validEntityBeforeSave(add);
        boolean flag = signTemplateMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SignTemplate
     *
     * @param bo SignTemplate
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SignTemplateBo bo) {
        SignTemplate update = MapstructUtils.convert(bo, SignTemplate.class);
        validEntityBeforeSave(update);
        return signTemplateMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SignTemplate entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SignTemplate信息
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
        return signTemplateMapper.deleteByIds(ids) > 0;
    }

}
