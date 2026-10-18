package org.dromara.system.domain.vo;

import org.dromara.system.domain.EmergencyPoint;
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
 * EmergencyPoint视图对象 emergency_point
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EmergencyPoint.class)
public class EmergencyPointVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_point
     */
    @ExcelProperty(value = "emergency_point")
    private Long id;

    /**
     * emergency_point
     */
    @ExcelProperty(value = "emergency_point")
    private String dutyOrgCode;

    /**
     * emergency_point
     */
    @ExcelProperty(value = "emergency_point")
    private String pointName;

    /**
     * emergency_point
     */
    @ExcelProperty(value = "emergency_point", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
