package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.HazardousMaterial;
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
 * HazardousMaterial视图对象 hazardous_material
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HazardousMaterial.class)
public class HazardousMaterialVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * hazardous_material
     */
    @ExcelProperty(value = "hazardous_material")
    private Long id;

    /**
     * hazardous_material
     */
    @ExcelProperty(value = "hazardous_material")
    private String articleCode;

    /**
     * hazardous_material
     */
    @ExcelProperty(value = "hazardous_material")
    private String articleName;

    /**
     * hazardous_material
     */
    @ExcelProperty(value = "hazardous_material")
    private String orgCodePath;

    /**
     * hazardous_material
     */
    @ExcelProperty(value = "hazardous_material", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
