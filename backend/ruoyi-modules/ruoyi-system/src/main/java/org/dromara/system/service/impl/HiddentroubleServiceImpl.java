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
import org.dromara.system.domain.bo.HiddentroubleBo;
import org.dromara.system.domain.vo.HiddentroubleVo;
import org.dromara.system.domain.Hiddentrouble;
import org.dromara.system.mapper.HiddentroubleMapper;
import org.dromara.system.service.IHiddentroubleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * HiddentroubleService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class HiddentroubleServiceImpl implements IHiddentroubleService {

    private final HiddentroubleMapper hiddentroubleMapper;

    /**
     * 查询Hiddentrouble
     *
     * @param id 主键
     * @return Hiddentrouble
     */
    @Override
    public HiddentroubleVo queryById(Long id) {
        return hiddentroubleMapper.selectVoById(id);
    }

    /**
     * 分页查询Hiddentrouble列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Hiddentrouble分页列表
     */
    @Override
    public PageResult<HiddentroubleVo> queryPageList(HiddentroubleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Hiddentrouble> lqw = buildQueryWrapper(bo);
        Page<HiddentroubleVo> result = hiddentroubleMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的Hiddentrouble列表
     *
     * @param bo 查询条件
     * @return Hiddentrouble列表
     */
    @Override
    public List<HiddentroubleVo> queryList(HiddentroubleBo bo) {
        LambdaQueryWrapper<Hiddentrouble> lqw = buildQueryWrapper(bo);
        return hiddentroubleMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<Hiddentrouble> buildQueryWrapper(HiddentroubleBo bo) {
        return QueryBuilder.lambda(Hiddentrouble.class)
            .eqIfText(Hiddentrouble::getStatus, bo.getStatus())
            .eqIfText(Hiddentrouble::getHiddenTroubleTypeName, bo.getHiddenTroubleTypeName())
            .eqIfText(Hiddentrouble::getHiddenTroubleRankName, bo.getHiddenTroubleRankName())
            .eqIfText(Hiddentrouble::getDiscoverOrgCode, bo.getDiscoverOrgCode())
            .eqIfText(Hiddentrouble::getDiscoverOrgCodePath, bo.getDiscoverOrgCodePath())
            .eqIfText(Hiddentrouble::getDiscoverUserCode, bo.getDiscoverUserCode())
            .eqIfText(Hiddentrouble::getDiscoverUserName, bo.getDiscoverUserName())
            .eqIfText(Hiddentrouble::getHiddenTroubleName, bo.getHiddenTroubleName())
            .orderByAsc(Hiddentrouble::getId)
            .build();
    }

    /**
     * 新增Hiddentrouble
     *
     * @param bo Hiddentrouble
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(HiddentroubleBo bo) {
        Hiddentrouble add = MapstructUtils.convert(bo, Hiddentrouble.class);
        validEntityBeforeSave(add);
        boolean flag = hiddentroubleMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Hiddentrouble
     *
     * @param bo Hiddentrouble
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(HiddentroubleBo bo) {
        Hiddentrouble update = MapstructUtils.convert(bo, Hiddentrouble.class);
        validEntityBeforeSave(update);
        return hiddentroubleMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Hiddentrouble entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除Hiddentrouble信息
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
        return hiddentroubleMapper.deleteByIds(ids) > 0;
    }

    // ==================== 隐患状态流转 ====================
    // 状态: 0=暂存, 1=提交, 2=整改, 3=验证, 4=关闭, 5=撤回

    /**
     * 提交隐患（暂存0→提交1）
     */
    @Override
    public Boolean submitHiddentrouble(Long id) {
        Hiddentrouble entity = hiddentroubleMapper.selectById(id);
        if (entity == null) {
            throw new RuntimeException("隐患记录不存在");
        }
        if (!"0".equals(entity.getStatus()) && !"5".equals(entity.getStatus())) {
            throw new RuntimeException("当前状态不允许提交，仅暂存或撤回状态可提交");
        }
        entity.setStatus("1");
        entity.setOperate("tj");
        entity.setSubmitTime(java.util.Date.from(java.time.LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        return hiddentroubleMapper.updateById(entity) > 0;
    }

    /**
     * 整改隐患（提交1→整改2）
     */
    @Override
    public Boolean rectifyHiddentrouble(Long id) {
        Hiddentrouble entity = hiddentroubleMapper.selectById(id);
        if (entity == null) {
            throw new RuntimeException("隐患记录不存在");
        }
        if (!"1".equals(entity.getStatus())) {
            throw new RuntimeException("当前状态不允许整改，仅提交状态可整改");
        }
        entity.setStatus("2");
        return hiddentroubleMapper.updateById(entity) > 0;
    }

    /**
     * 验证隐患（整改2→验证3）
     */
    @Override
    public Boolean verifyHiddentrouble(Long id) {
        Hiddentrouble entity = hiddentroubleMapper.selectById(id);
        if (entity == null) {
            throw new RuntimeException("隐患记录不存在");
        }
        if (!"2".equals(entity.getStatus())) {
            throw new RuntimeException("当前状态不允许验证，仅整改状态可验证");
        }
        entity.setStatus("3");
        return hiddentroubleMapper.updateById(entity) > 0;
    }

    /**
     * 关闭隐患（验证3→关闭4）
     */
    @Override
    public Boolean closeHiddentrouble(Long id) {
        Hiddentrouble entity = hiddentroubleMapper.selectById(id);
        if (entity == null) {
            throw new RuntimeException("隐患记录不存在");
        }
        if (!"3".equals(entity.getStatus())) {
            throw new RuntimeException("当前状态不允许关闭，仅验证状态可关闭");
        }
        entity.setStatus("4");
        entity.setCloseTime(java.util.Date.from(java.time.LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        return hiddentroubleMapper.updateById(entity) > 0;
    }

    /**
     * 撤回隐患（提交1→撤回5）
     */
    @Override
    public Boolean revokeHiddentrouble(Long id) {
        Hiddentrouble entity = hiddentroubleMapper.selectById(id);
        if (entity == null) {
            throw new RuntimeException("隐患记录不存在");
        }
        if (!"1".equals(entity.getStatus())) {
            throw new RuntimeException("当前状态不允许撤回，仅提交状态可撤回");
        }
        entity.setStatus("5");
        entity.setOperate("zc");
        return hiddentroubleMapper.updateById(entity) > 0;
    }

}
