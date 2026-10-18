package org.dromara.system.domain.bo;

import org.dromara.system.domain.CertificateConfig;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * CertificateConfig业务对象 certificate_config
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = CertificateConfig.class, reverseConvertGenerate = false)
public class CertificateConfigBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * certificate_config
     */
    @NotNull(message = "certificate_config不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * certificate_config
     */
    @NotBlank(message = "certificate_config不能为空", groups = { AddGroup.class, EditGroup.class })
    private String certificateTypeValue;

    /**
     * certificate_config
     */
    @NotBlank(message = "certificate_config不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * certificate_config
     */
    @NotNull(message = "certificate_config不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer validDays;

    /**
     * certificate_config
     */
    @NotNull(message = "certificate_config不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer expireWarningDays;

    /**
     * certificate_config
     */
    @NotBlank(message = "certificate_config不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
