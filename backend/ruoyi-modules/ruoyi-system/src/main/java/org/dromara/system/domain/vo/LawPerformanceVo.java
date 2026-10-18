package org.dromara.system.domain.vo;

import org.dromara.system.domain.LawPerformance;
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
 * LawPerformance视图对象 law_performance
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = LawPerformance.class)
public class LawPerformanceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * law_performance
     */
    @ExcelProperty(value = "law_performance")
    private Long id;

    /**
     * law_performance
     */
    @ExcelProperty(value = "law_performance")
    private Date endTime;

    /**
     * law_performance
     */
    @ExcelProperty(value = "law_performance")
    private String orgCodePath;

    /**
     * law_performance
     */
    @ExcelProperty(value = "law_performance", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
