package org.dromara.system.domain.vo;

import org.dromara.system.domain.EmergencyDrill;
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
 * EmergencyDrill视图对象 emergency_drill
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EmergencyDrill.class)
public class EmergencyDrillVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_drill
     */
    @ExcelProperty(value = "emergency_drill")
    private Long id;

    /**
     * emergency_drill
     */
    @ExcelProperty(value = "emergency_drill")
    private String emergencyName;

    /**
     * emergency_drill
     */
    @ExcelProperty(value = "emergency_drill")
    private String orgCodes;

    /**
     * emergency_drill
     */
    @ExcelProperty(value = "emergency_drill")
    private Date drillTime;

    /**
     * emergency_drill
     */
    @ExcelProperty(value = "emergency_drill", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
