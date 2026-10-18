package org.dromara.system.domain.vo;

import org.dromara.system.domain.SignTemplate;
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
 * SignTemplate视图对象 sign_template
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SignTemplate.class)
public class SignTemplateVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * sign_template
     */
    @ExcelProperty(value = "sign_template")
    private Long id;

    /**
     * sign_template
     */
    @ExcelProperty(value = "sign_template")
    private String templateName;

    /**
     * sign_template
     */
    @ExcelProperty(value = "sign_template", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
