package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.Accident;
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
 * Accident视图对象 accident
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Accident.class)
public class AccidentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * accident
     */
    @ExcelProperty(value = "accident")
    private Long id;

    /**
     * accident
     */
    @ExcelProperty(value = "accident")
    private String theirOrgCode;

    /**
     * accident
     */
    @ExcelProperty(value = "accident")
    private String accidentName;

    /**
     * accident
     */
    @ExcelProperty(value = "accident")
    private Date accidentTime;

    /**
     * accident
     */
    @ExcelProperty(value = "accident", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
