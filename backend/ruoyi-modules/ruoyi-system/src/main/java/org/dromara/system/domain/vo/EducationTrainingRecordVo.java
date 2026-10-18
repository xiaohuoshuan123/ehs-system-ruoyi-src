package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.EducationTrainingRecord;
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

/**
 * EducationTrainingRecord视图对象 education_training_record
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EducationTrainingRecord.class)
public class EducationTrainingRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * education_training_record
     */
    @ExcelProperty(value = "education_training_record")
    private Long id;

    /**
     * education_training_record
     */
    @ExcelProperty(value = "education_training_record")
    private String orgCode1;

    /**
     * education_training_record
     */
    @ExcelProperty(value = "education_training_record")
    private String empCode;

    /**
     * education_training_record
     */
    @ExcelProperty(value = "education_training_record")
    private String empName;

    /**
     * education_training_record
     */
    @ExcelProperty(value = "education_training_record", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
