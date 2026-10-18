package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * CompanyRegulation对象 company_regulation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("company_regulation")
public class CompanyRegulation extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * company_regulation
     */
    private Long id;

    /**
     * company_regulation
     */
    private String manageOrgCode;

    /**
     * company_regulation
     */
    private String manageOrgName;

    /**
     * company_regulation
     */
    private String lawRegulations;

    /**
     * company_regulation
     */
    private String status;


}
