package org.dromara.system.domain.vo;

import org.dromara.system.domain.EmergencyEquipment;
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
 * EmergencyEquipment视图对象 emergency_equipment
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EmergencyEquipment.class)
public class EmergencyEquipmentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_equipment
     */
    @ExcelProperty(value = "emergency_equipment")
    private Long id;

    /**
     * emergency_equipment
     */
    @ExcelProperty(value = "emergency_equipment")
    private String equipmentName;

    /**
     * emergency_equipment
     */
    @ExcelProperty(value = "emergency_equipment")
    private Date lastCheckDate;

    /**
     * emergency_equipment
     */
    @ExcelProperty(value = "emergency_equipment")
    private Date nextCheckDate;

    /**
     * emergency_equipment
     */
    @ExcelProperty(value = "emergency_equipment", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
