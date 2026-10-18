package org.dromara.system.domain.vo;

import org.dromara.system.domain.EmergencyMaterial;
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
 * EmergencyMaterial视图对象 emergency_material
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EmergencyMaterial.class)
public class EmergencyMaterialVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_material
     */
    @ExcelProperty(value = "emergency_material")
    private Long id;

    /**
     * emergency_material
     */
    @ExcelProperty(value = "emergency_material")
    private String orgCodeId;

    /**
     * emergency_material
     */
    @ExcelProperty(value = "emergency_material")
    private String materialName;

    /**
     * emergency_material
     */
    @ExcelProperty(value = "emergency_material")
    private Date checkDate;

    /**
     * emergency_material
     */
    @ExcelProperty(value = "emergency_material", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
