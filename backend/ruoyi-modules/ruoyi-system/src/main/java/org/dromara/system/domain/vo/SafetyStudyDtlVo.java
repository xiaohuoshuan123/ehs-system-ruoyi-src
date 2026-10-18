package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.SafetyStudyDtl;
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
 * SafetyStudyDtl视图对象 safety_study_dtl
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SafetyStudyDtl.class)
public class SafetyStudyDtlVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_study_dtl
     */
    @ExcelProperty(value = "safety_study_dtl")
    private Long id;

    /**
     * safety_study_dtl
     */
    @ExcelProperty(value = "safety_study_dtl")
    private String empCode;

    /**
     * safety_study_dtl
     */
    @ExcelProperty(value = "safety_study_dtl")
    private String empName;

    /**
     * safety_study_dtl
     */
    @ExcelProperty(value = "safety_study_dtl")
    private Date studyTime;


}
