package org.dromara.system.domain.bo;

import org.dromara.system.domain.CompanyRegulation;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * CompanyRegulation业务对象 company_regulation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = CompanyRegulation.class, reverseConvertGenerate = false)
public class CompanyRegulationBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * company_regulation
     */
    @NotNull(message = "company_regulation不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * company_regulation
     */
    @NotBlank(message = "company_regulation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String manageOrgCode;

    /**
     * company_regulation
     */
    @NotBlank(message = "company_regulation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String manageOrgName;

    /**
     * company_regulation
     */
    @NotBlank(message = "company_regulation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String lawRegulations;

    /**
     * company_regulation
     */
    @NotBlank(message = "company_regulation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
