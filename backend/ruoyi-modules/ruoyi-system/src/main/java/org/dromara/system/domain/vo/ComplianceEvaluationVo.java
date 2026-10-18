package org.dromara.system.domain.vo;

import org.dromara.system.domain.ComplianceEvaluation;
import org.apache.fesod.sheet.annotation.ExcelIgnoreUnannotated;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * ComplianceEvaluation视图对象 compliance_evaluation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ComplianceEvaluation.class)
public class ComplianceEvaluationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * compliance_evaluation
     */
    @ExcelProperty(value = "compliance_evaluation")
    private Long id;

    /**
     * compliance_evaluation
     */
    @ExcelProperty(value = "compliance_evaluation")
    private String manageOrgCode;

    /**
     * compliance_evaluation
     */
    @ExcelProperty(value = "compliance_evaluation")
    private Date evaluateDate;

    /**
     * compliance_evaluation
     */
    @ExcelProperty(value = "compliance_evaluation", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
