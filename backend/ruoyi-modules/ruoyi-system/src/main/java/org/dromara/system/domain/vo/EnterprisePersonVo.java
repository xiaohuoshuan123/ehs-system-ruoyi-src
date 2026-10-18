package org.dromara.system.domain.vo;

import org.dromara.system.domain.EnterprisePerson;
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
 * EnterprisePerson视图对象 enterprise_person
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EnterprisePerson.class)
public class EnterprisePersonVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person")
    private Long id;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person")
    private String empCode;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person")
    private String empName;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person")
    private String orgCodePath;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person")
    private String orgCode;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person")
    private Date issueDate;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person")
    private Date expireDate;

    /**
     * enterprise_person
     */
    @ExcelProperty(value = "enterprise_person", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
