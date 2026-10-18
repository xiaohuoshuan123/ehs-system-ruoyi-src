package org.dromara.system.domain.vo;

import org.dromara.system.domain.BrscoreStandard;
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
 * BrscoreStandard视图对象 brscore_standard
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BrscoreStandard.class)
public class BrscoreStandardVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * brscore_standard
     */
    @ExcelProperty(value = "brscore_standard")
    private Long id;

    /**
     * brscore_standard
     */
    @ExcelProperty(value = "brscore_standard")
    private String orgCode;

    /**
     * brscore_standard
     */
    @ExcelProperty(value = "brscore_standard")
    private String orgCodePath;

    /**
     * brscore_standard
     */
    @ExcelProperty(value = "brscore_standard", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
