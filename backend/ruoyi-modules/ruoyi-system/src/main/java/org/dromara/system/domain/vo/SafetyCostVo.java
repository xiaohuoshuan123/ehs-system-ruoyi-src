package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.SafetyCost;
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
 * SafetyCost视图对象 safety_cost
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SafetyCost.class)
public class SafetyCostVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_cost
     */
    @ExcelProperty(value = "safety_cost")
    private Long id;

    /**
     * safety_cost
     */
    @ExcelProperty(value = "safety_cost")
    private String orgCodePath;

    /**
     * safety_cost
     */
    @ExcelProperty(value = "safety_cost")
    private String costName;

    /**
     * safety_cost
     */
    @ExcelProperty(value = "safety_cost", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
