package org.dromara.system.domain.bo;

import org.dromara.system.domain.ComplianceEvaluation;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * ComplianceEvaluation业务对象 compliance_evaluation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = ComplianceEvaluation.class, reverseConvertGenerate = false)
public class ComplianceEvaluationBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * compliance_evaluation
     */
    @NotNull(message = "compliance_evaluation不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * compliance_evaluation
     */
    @NotBlank(message = "compliance_evaluation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String manageOrgCode;

    /**
     * compliance_evaluation
     */
    @NotBlank(message = "compliance_evaluation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String year;

    /**
     * compliance_evaluation
     */
    @NotBlank(message = "compliance_evaluation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String evaluationResult;

    /**
     * compliance_evaluation
     */
    @NotBlank(message = "compliance_evaluation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String evaluator;

    /**
     * compliance_evaluation
     */
    @NotNull(message = "compliance_evaluation不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date evaluateDate;

    /**
     * compliance_evaluation
     */
    @NotBlank(message = "compliance_evaluation不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
