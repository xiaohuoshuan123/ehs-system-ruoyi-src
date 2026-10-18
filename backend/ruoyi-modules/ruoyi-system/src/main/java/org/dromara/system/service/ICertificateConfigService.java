package org.dromara.system.service;

import org.dromara.system.domain.vo.CertificateConfigVo;
import org.dromara.system.domain.bo.CertificateConfigBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * CertificateConfigService接口
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
public interface ICertificateConfigService {

    /**
     * 查询CertificateConfig
     *
     * @param id 主键
     * @return CertificateConfig
     */
    CertificateConfigVo queryById(Long id);

    /**
     * 分页查询CertificateConfig列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return CertificateConfig分页列表
     */
    PageResult<CertificateConfigVo> queryPageList(CertificateConfigBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的CertificateConfig列表
     *
     * @param bo 查询条件
     * @return CertificateConfig列表
     */
    List<CertificateConfigVo> queryList(CertificateConfigBo bo);


    /**
     * 新增CertificateConfig
     *
     * @param bo CertificateConfig
     * @return 是否新增成功
     */
    Boolean insertByBo(CertificateConfigBo bo);

    /**
     * 修改CertificateConfig
     *
     * @param bo CertificateConfig
     * @return 是否修改成功
     */
    Boolean updateByBo(CertificateConfigBo bo);



    /**
     * 校验并批量删除CertificateConfig信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
