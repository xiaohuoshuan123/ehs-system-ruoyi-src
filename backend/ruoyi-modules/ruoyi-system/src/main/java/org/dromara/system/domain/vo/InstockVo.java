package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.Instock;
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
 * Instock视图对象 instock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Instock.class)
public class InstockVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * instock
     */
    @ExcelProperty(value = "instock")
    private Long id;

    /**
     * instock
     */
    @ExcelProperty(value = "instock")
    private String articleCode;

    /**
     * instock
     */
    @ExcelProperty(value = "instock")
    private String articleName;

    /**
     * instock
     */
    @ExcelProperty(value = "instock")
    private String storageName;

    /**
     * instock
     */
    @ExcelProperty(value = "instock")
    private Date inTime;

    /**
     * instock
     */
    @ExcelProperty(value = "instock", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
