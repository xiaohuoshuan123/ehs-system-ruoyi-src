package org.dromara.system.domain.vo;

import org.dromara.system.domain.EmergencyPlan;
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
 * EmergencyPlan视图对象 emergency_plan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EmergencyPlan.class)
public class EmergencyPlanVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_plan
     */
    @ExcelProperty(value = "emergency_plan")
    private Long id;

    /**
     * emergency_plan
     */
    @ExcelProperty(value = "emergency_plan")
    private String emerName;

    /**
     * emergency_plan
     */
    @ExcelProperty(value = "emergency_plan")
    private String orgCodes;

    /**
     * emergency_plan
     */
    @ExcelProperty(value = "emergency_plan")
    private Date reviewDate;

    /**
     * emergency_plan
     */
    @ExcelProperty(value = "emergency_plan")
    private Date effectiveDate;

    /**
     * emergency_plan
     */
    @ExcelProperty(value = "emergency_plan", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
