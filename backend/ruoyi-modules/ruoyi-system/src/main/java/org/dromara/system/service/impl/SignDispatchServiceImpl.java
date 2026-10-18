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
import org.dromara.system.domain.bo.SignDispatchBo;
import org.dromara.system.domain.vo.SignDispatchVo;
import org.dromara.system.domain.SignDispatch;
import org.dromara.system.mapper.SignDispatchMapper;
import org.dromara.system.service.ISignDispatchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SignDispatchService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SignDispatchServiceImpl implements ISignDispatchService {

    private final SignDispatchMapper signDispatchMapper;

    /**
     * 查询SignDispatch
     *
     * @param id 主键
     * @return SignDispatch
     */
    @Override
    public SignDispatchVo queryById(Long id) {
        return signDispatchMapper.selectVoById(id);
    }

    /**
     * 分页查询SignDispatch列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SignDispatch分页列表
     */
    @Override
    public PageResult<SignDispatchVo> queryPageList(SignDispatchBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SignDispatch> lqw = buildQueryWrapper(bo);
        Page<SignDispatchVo> result = signDispatchMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的SignDispatch列表
     *
     * @param bo 查询条件
     * @return SignDispatch列表
     */
    @Override
    public List<SignDispatchVo> queryList(SignDispatchBo bo) {
        LambdaQueryWrapper<SignDispatch> lqw = buildQueryWrapper(bo);
        return signDispatchMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<SignDispatch> buildQueryWrapper(SignDispatchBo bo) {
        return QueryBuilder.lambda(SignDispatch.class)
            .eqIfText(SignDispatch::getEmpName, bo.getEmpName())
            .eqIfText(SignDispatch::getStatus, bo.getStatus())
            .orderByAsc(SignDispatch::getId)
            .build();
    }

    /**
     * 新增SignDispatch
     *
     * @param bo SignDispatch
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SignDispatchBo bo) {
        SignDispatch add = MapstructUtils.convert(bo, SignDispatch.class);
        validEntityBeforeSave(add);
        boolean flag = signDispatchMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改SignDispatch
     *
     * @param bo SignDispatch
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SignDispatchBo bo) {
        SignDispatch update = MapstructUtils.convert(bo, SignDispatch.class);
        validEntityBeforeSave(update);
        return signDispatchMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SignDispatch entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除SignDispatch信息
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
        return signDispatchMapper.deleteByIds(ids) > 0;
    }

}
