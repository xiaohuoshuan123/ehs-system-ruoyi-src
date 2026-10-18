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
import org.dromara.system.domain.bo.RiskControlListBo;
import org.dromara.system.domain.vo.RiskControlListVo;
import org.dromara.system.domain.RiskControlList;
import org.dromara.system.mapper.RiskControlListMapper;
import org.dromara.system.service.IRiskControlListService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * RiskControlListService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RiskControlListServiceImpl implements IRiskControlListService {

    private final RiskControlListMapper riskControlListMapper;

    /**
     * 查询RiskControlList
     *
     * @param id 主键
     * @return RiskControlList
     */
    @Override
    public RiskControlListVo queryById(Long id) {
        return riskControlListMapper.selectVoById(id);
    }

    /**
     * 分页查询RiskControlList列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return RiskControlList分页列表
     */
    @Override
    public PageResult<RiskControlListVo> queryPageList(RiskControlListBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<RiskControlList> lqw = buildQueryWrapper(bo);
        Page<RiskControlListVo> result = riskControlListMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的RiskControlList列表
     *
     * @param bo 查询条件
     * @return RiskControlList列表
     */
    @Override
    public List<RiskControlListVo> queryList(RiskControlListBo bo) {
        LambdaQueryWrapper<RiskControlList> lqw = buildQueryWrapper(bo);
        return riskControlListMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<RiskControlList> buildQueryWrapper(RiskControlListBo bo) {
        return QueryBuilder.lambda(RiskControlList.class)
            .eqIfText(RiskControlList::getRiskName, bo.getRiskName())
            .eqIfText(RiskControlList::getStatus, bo.getStatus())
            .orderByAsc(RiskControlList::getId)
            .build();
    }

    /**
     * 新增RiskControlList
     *
     * @param bo RiskControlList
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(RiskControlListBo bo) {
        RiskControlList add = MapstructUtils.convert(bo, RiskControlList.class);
        validEntityBeforeSave(add);
        boolean flag = riskControlListMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改RiskControlList
     *
     * @param bo RiskControlList
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(RiskControlListBo bo) {
        RiskControlList update = MapstructUtils.convert(bo, RiskControlList.class);
        validEntityBeforeSave(update);
        return riskControlListMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(RiskControlList entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除RiskControlList信息
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
        return riskControlListMapper.deleteByIds(ids) > 0;
    }

}
