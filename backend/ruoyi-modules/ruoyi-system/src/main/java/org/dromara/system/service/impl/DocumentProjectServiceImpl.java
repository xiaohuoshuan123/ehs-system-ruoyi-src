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
import org.dromara.system.domain.bo.DocumentProjectBo;
import org.dromara.system.domain.vo.DocumentProjectVo;
import org.dromara.system.domain.DocumentProject;
import org.dromara.system.mapper.DocumentProjectMapper;
import org.dromara.system.service.IDocumentProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * DocumentProjectService业务层处理
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentProjectServiceImpl implements IDocumentProjectService {

    private final DocumentProjectMapper documentProjectMapper;

    /**
     * 查询DocumentProject
     *
     * @param id 主键
     * @return DocumentProject
     */
    @Override
    public DocumentProjectVo queryById(Long id) {
        return documentProjectMapper.selectVoById(id);
    }

    /**
     * 分页查询DocumentProject列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return DocumentProject分页列表
     */
    @Override
    public PageResult<DocumentProjectVo> queryPageList(DocumentProjectBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DocumentProject> lqw = buildQueryWrapper(bo);
        Page<DocumentProjectVo> result = documentProjectMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的DocumentProject列表
     *
     * @param bo 查询条件
     * @return DocumentProject列表
     */
    @Override
    public List<DocumentProjectVo> queryList(DocumentProjectBo bo) {
        LambdaQueryWrapper<DocumentProject> lqw = buildQueryWrapper(bo);
        return documentProjectMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<DocumentProject> buildQueryWrapper(DocumentProjectBo bo) {
        return QueryBuilder.lambda(DocumentProject.class)
            .eqIfText(DocumentProject::getDutyOrgCode, bo.getDutyOrgCode())
            .eqIfText(DocumentProject::getProjectName, bo.getProjectName())
            .eqIfText(DocumentProject::getStatus, bo.getStatus())
            .orderByAsc(DocumentProject::getId)
            .build();
    }

    /**
     * 新增DocumentProject
     *
     * @param bo DocumentProject
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DocumentProjectBo bo) {
        DocumentProject add = MapstructUtils.convert(bo, DocumentProject.class);
        validEntityBeforeSave(add);
        boolean flag = documentProjectMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改DocumentProject
     *
     * @param bo DocumentProject
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DocumentProjectBo bo) {
        DocumentProject update = MapstructUtils.convert(bo, DocumentProject.class);
        validEntityBeforeSave(update);
        return documentProjectMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DocumentProject entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除DocumentProject信息
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
        return documentProjectMapper.deleteByIds(ids) > 0;
    }

}
