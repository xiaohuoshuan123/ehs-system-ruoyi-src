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
import org.dromara.system.domain.bo.CertificateConfigBo;
import org.dromara.system.domain.vo.CertificateConfigVo;
import org.dromara.system.domain.CertificateConfig;
import org.dromara.system.mapper.CertificateConfigMapper;
import org.dromara.system.service.ICertificateConfigService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * CertificateConfigService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CertificateConfigServiceImpl implements ICertificateConfigService {

    private final CertificateConfigMapper certificateConfigMapper;

    /**
     * 查询CertificateConfig
     *
     * @param id 主键
     * @return CertificateConfig
     */
    @Override
    public CertificateConfigVo queryById(Long id) {
        return certificateConfigMapper.selectVoById(id);
    }

    /**
     * 分页查询CertificateConfig列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return CertificateConfig分页列表
     */
    @Override
    public PageResult<CertificateConfigVo> queryPageList(CertificateConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CertificateConfig> lqw = buildQueryWrapper(bo);
        Page<CertificateConfigVo> result = certificateConfigMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的CertificateConfig列表
     *
     * @param bo 查询条件
     * @return CertificateConfig列表
     */
    @Override
    public List<CertificateConfigVo> queryList(CertificateConfigBo bo) {
        LambdaQueryWrapper<CertificateConfig> lqw = buildQueryWrapper(bo);
        return certificateConfigMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<CertificateConfig> buildQueryWrapper(CertificateConfigBo bo) {
        return QueryBuilder.lambda(CertificateConfig.class)
            .eqIfText(CertificateConfig::getStatus, bo.getStatus())
            .orderByAsc(CertificateConfig::getId)
            .build();
    }

    /**
     * 新增CertificateConfig
     *
     * @param bo CertificateConfig
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(CertificateConfigBo bo) {
        CertificateConfig add = MapstructUtils.convert(bo, CertificateConfig.class);
        validEntityBeforeSave(add);
        boolean flag = certificateConfigMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改CertificateConfig
     *
     * @param bo CertificateConfig
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(CertificateConfigBo bo) {
        CertificateConfig update = MapstructUtils.convert(bo, CertificateConfig.class);
        validEntityBeforeSave(update);
        return certificateConfigMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CertificateConfig entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除CertificateConfig信息
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
        return certificateConfigMapper.deleteByIds(ids) > 0;
    }

}
