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
import org.dromara.system.domain.bo.SafetyStudyMstBo;
import org.dromara.system.domain.vo.SafetyStudyMstVo;
import org.dromara.system.domain.SafetyStudyMst;
import org.dromara.system.mapper.SafetyStudyMstMapper;
import org.dromara.system.service.ISafetyStudyMstService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SafetyStudyMstService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SafetyStudyMstServiceImpl implements ISafetyStudyMstService {

    private final SafetyStudyMstMapper safetyStudyMstMapper;

    /**
     * 查询SafetyStudyMst
     *
     * @param id 主键
     * @return SafetyStudyMst
     */
    @Override
    public SafetyStudyMstVo queryById(Long id) {
        return safetyStudyMstMapper.selectVoById(id);
    }

    /**
     * 分页查询SafetyStudyMst列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SafetyStudyMst分页列表
     */
    @Override
    public PageResult<SafetyStudyMstVo> queryPageList(SafetyStudyMstBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SafetyStudyMst> lqw = buildQueryWrapper(bo);
        Page<SafetyStudyMstVo> result = safetyStudyMstMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SafetyStudyMst列表
     *
     * @param bo 查询条件
     * @return SafetyStudyMst列表
     */
    @Override
    public List<SafetyStudyMstVo> queryList(SafetyStudyMstBo bo) {
        LambdaQueryWrapper<SafetyStudyMst> lqw = buildQueryWrapper(bo);
        return safetyStudyMstMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SafetyStudyMst> buildQueryWrapper(SafetyStudyMstBo bo) {
        return QueryBuilder.lambda(SafetyStudyMst.class)
            .eqIfText(SafetyStudyMst::getStatus, bo.getStatus())
            .orderByAsc(SafetyStudyMst::getId)
            .build();
    }

    /**
     * 新增SafetyStudyMst
     *
     * @param bo SafetyStudyMst
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SafetyStudyMstBo bo) {
        SafetyStudyMst add = MapstructUtils.convert(bo, SafetyStudyMst.class);
        validEntityBeforeSave(add);
        boolean flag = safetyStudyMstMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SafetyStudyMst
     *
     * @param bo SafetyStudyMst
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SafetyStudyMstBo bo) {
        SafetyStudyMst update = MapstructUtils.convert(bo, SafetyStudyMst.class);
        validEntityBeforeSave(update);
        return safetyStudyMstMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SafetyStudyMst entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SafetyStudyMst信息
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
        return safetyStudyMstMapper.deleteByIds(ids) > 0;
    }

}
