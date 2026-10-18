package org.dromara.system.domain.vo;

import org.dromara.system.domain.LawModel;
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
 * LawModel视图对象 law_model
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = LawModel.class)
public class LawModelVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * law_model
     */
    @ExcelProperty(value = "law_model")
    private Long id;

    /**
     * law_model
     */
    @ExcelProperty(value = "law_model")
    private String targetCode;

    /**
     * law_model
     */
    @ExcelProperty(value = "law_model")
    private String secendModuleName;

    /**
     * law_model
     */
    @ExcelProperty(value = "law_model", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
