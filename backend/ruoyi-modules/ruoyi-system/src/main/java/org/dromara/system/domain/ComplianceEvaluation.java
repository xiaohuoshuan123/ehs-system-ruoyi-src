package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * ComplianceEvaluation对象 compliance_evaluation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("compliance_evaluation")
public class ComplianceEvaluation extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * compliance_evaluation
     */
    private Long id;

    /**
     * compliance_evaluation
     */
    private String manageOrgCode;

    /**
     * compliance_evaluation
     */
    private String year;

    /**
     * compliance_evaluation
     */
    private String evaluationResult;

    /**
     * compliance_evaluation
     */
    private String evaluator;

    /**
     * compliance_evaluation
     */
    private Date evaluateDate;

    /**
     * compliance_evaluation
     */
    private String status;


}
