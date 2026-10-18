package org.dromara.system.domain.vo;

import org.dromara.system.domain.SpecialWork;
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
 * SpecialWork视图对象 special_work
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SpecialWork.class)
public class SpecialWorkVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * special_work
     */
    @ExcelProperty(value = "special_work")
    private Long id;

    /**
     * special_work
     */
    @ExcelProperty(value = "special_work")
    private String empCode;

    /**
     * special_work
     */
    @ExcelProperty(value = "special_work")
    private String empName;

    /**
     * special_work
     */
    @ExcelProperty(value = "special_work")
    private String orgCodePath;

    /**
     * special_work
     */
    @ExcelProperty(value = "special_work")
    private Date issueDate;

    /**
     * special_work
     */
    @ExcelProperty(value = "special_work")
    private Date expireDate;

    /**
     * special_work
     */
    @ExcelProperty(value = "special_work", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
