package org.dromara.system.domain.vo;

import org.dromara.system.domain.Msds;
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
 * Msds视图对象 msds
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Msds.class)
public class MsdsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * msds
     */
    @ExcelProperty(value = "msds")
    private Long id;

    /**
     * msds
     */
    @ExcelProperty(value = "msds")
    private String articleCode;

    /**
     * msds
     */
    @ExcelProperty(value = "msds")
    private String articleName;

    /**
     * msds
     */
    @ExcelProperty(value = "msds")
    private String issueOrgCode;

    /**
     * msds
     */
    @ExcelProperty(value = "msds", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
