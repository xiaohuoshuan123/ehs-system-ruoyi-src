package org.dromara.system.domain.vo;

import org.dromara.system.domain.CertificateConfig;
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
 * CertificateConfig视图对象 certificate_config
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = CertificateConfig.class)
public class CertificateConfigVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * certificate_config
     */
    @ExcelProperty(value = "certificate_config")
    private Long id;

    /**
     * certificate_config
     */
    @ExcelProperty(value = "certificate_config", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
