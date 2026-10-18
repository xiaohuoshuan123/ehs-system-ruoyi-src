package org.dromara.system.domain.vo;

import org.dromara.system.domain.Architecture;
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
 * Architecture视图对象 architecture
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Architecture.class)
public class ArchitectureVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * architecture
     */
    @ExcelProperty(value = "architecture")
    private Long id;

    /**
     * architecture
     */
    @ExcelProperty(value = "architecture")
    private String orgCodePath;

    /**
     * architecture
     */
    @ExcelProperty(value = "architecture")
    private String orgCode;

    /**
     * architecture
     */
    @ExcelProperty(value = "architecture")
    private String orgName;

    /**
     * architecture
     */
    @ExcelProperty(value = "architecture")
    private String parentCode;

    /**
     * architecture
     */
    @ExcelProperty(value = "architecture", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
