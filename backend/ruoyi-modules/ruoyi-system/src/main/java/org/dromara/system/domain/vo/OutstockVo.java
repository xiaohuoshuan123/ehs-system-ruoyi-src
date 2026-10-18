package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.Outstock;
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
 * Outstock视图对象 outstock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Outstock.class)
public class OutstockVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * outstock
     */
    @ExcelProperty(value = "outstock")
    private Long id;

    /**
     * outstock
     */
    @ExcelProperty(value = "outstock")
    private String articleCode;

    /**
     * outstock
     */
    @ExcelProperty(value = "outstock")
    private String articleName;

    /**
     * outstock
     */
    @ExcelProperty(value = "outstock")
    private String storageName;

    /**
     * outstock
     */
    @ExcelProperty(value = "outstock")
    private Date outTime;

    /**
     * outstock
     */
    @ExcelProperty(value = "outstock", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
