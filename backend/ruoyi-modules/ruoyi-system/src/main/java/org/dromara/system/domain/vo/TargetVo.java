package org.dromara.system.domain.vo;

import org.dromara.system.domain.Target;
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
 * Target视图对象 target
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Target.class)
public class TargetVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * target
     */
    @ExcelProperty(value = "target")
    private Long id;

    /**
     * target
     */
    @ExcelProperty(value = "target")
    private String orgCodePath;

    /**
     * target
     */
    @ExcelProperty(value = "target")
    private String orgCode;

    /**
     * target
     */
    @ExcelProperty(value = "target")
    private String targetName;

    /**
     * target
     */
    @ExcelProperty(value = "target", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
