package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.Stock;
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
 * Stock视图对象 stock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Stock.class)
public class StockVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * stock
     */
    @ExcelProperty(value = "stock")
    private Long id;

    /**
     * stock
     */
    @ExcelProperty(value = "stock")
    private String storageName;

    /**
     * stock
     */
    @ExcelProperty(value = "stock")
    private String articleName;

    /**
     * stock
     */
    @ExcelProperty(value = "stock")
    private String articleCode;

    /**
     * stock
     */
    @ExcelProperty(value = "stock", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
