package org.dromara.system.domain.vo;

import org.dromara.system.domain.SafetyDuty;
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
 * SafetyDuty视图对象 safety_duty
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SafetyDuty.class)
public class SafetyDutyVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_duty
     */
    @ExcelProperty(value = "safety_duty")
    private Long id;

    /**
     * safety_duty
     */
    @ExcelProperty(value = "safety_duty")
    private String orgCodePath;

    /**
     * safety_duty
     */
    @ExcelProperty(value = "safety_duty")
    private String orgCode;

    /**
     * safety_duty
     */
    @ExcelProperty(value = "safety_duty")
    private String orgName;

    /**
     * safety_duty
     */
    @ExcelProperty(value = "safety_duty")
    private String jobName;

    /**
     * safety_duty
     */
    @ExcelProperty(value = "safety_duty", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
