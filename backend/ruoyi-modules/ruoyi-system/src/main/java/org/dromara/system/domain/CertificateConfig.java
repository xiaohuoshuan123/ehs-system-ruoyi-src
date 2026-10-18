package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * CertificateConfig对象 certificate_config
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("certificate_config")
public class CertificateConfig extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * certificate_config
     */
    private Long id;

    /**
     * certificate_config
     */
    private String certificateTypeValue;

    /**
     * certificate_config
     */
    private String title;

    /**
     * certificate_config
     */
    private Integer validDays;

    /**
     * certificate_config
     */
    private Integer expireWarningDays;

    /**
     * certificate_config
     */
    private String status;


}
