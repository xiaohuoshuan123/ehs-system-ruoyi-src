package org.dromara.system.domain.vo;

import org.dromara.system.domain.CompanyRegulation;
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
 * CompanyRegulation视图对象 company_regulation
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = CompanyRegulation.class)
public class CompanyRegulationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * company_regulation
     */
    @ExcelProperty(value = "company_regulation")
    private Long id;

    /**
     * company_regulation
     */
    @ExcelProperty(value = "company_regulation")
    private String manageOrgCode;

    /**
     * company_regulation
     */
    @ExcelProperty(value = "company_regulation")
    private String manageOrgName;

    /**
     * company_regulation
     */
    @ExcelProperty(value = "company_regulation", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
