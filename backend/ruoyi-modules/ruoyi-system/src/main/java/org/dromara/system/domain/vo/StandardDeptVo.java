package org.dromara.system.domain.vo;

import org.dromara.system.domain.StandardDept;
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
 * StandardDept视图对象 standard_dept
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = StandardDept.class)
public class StandardDeptVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * standard_dept
     */
    @ExcelProperty(value = "standard_dept")
    private Long id;

    /**
     * standard_dept
     */
    @ExcelProperty(value = "standard_dept")
    private String orgCodePath;

    /**
     * standard_dept
     */
    @ExcelProperty(value = "standard_dept")
    private String deptName;

    /**
     * standard_dept
     */
    @ExcelProperty(value = "standard_dept")
    private String deptCode;

    /**
     * standard_dept
     */
    @ExcelProperty(value = "standard_dept", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
