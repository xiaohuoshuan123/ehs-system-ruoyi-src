package org.dromara.system.domain.vo;

import org.dromara.system.domain.BreachRecord;
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
 * BreachRecord视图对象 breach_record
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BreachRecord.class)
public class BreachRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * breach_record
     */
    @ExcelProperty(value = "breach_record")
    private Long id;

    /**
     * breach_record
     */
    @ExcelProperty(value = "breach_record")
    private String checkOrgCode;

    /**
     * breach_record
     */
    @ExcelProperty(value = "breach_record")
    private String checkOrgCodePath;

    /**
     * breach_record
     */
    @ExcelProperty(value = "breach_record")
    private Date breachTime;

    /**
     * breach_record
     */
    @ExcelProperty(value = "breach_record", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
