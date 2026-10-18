package org.dromara.system.domain.vo;

import org.dromara.system.domain.Laws;
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
 * Laws视图对象 laws
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Laws.class)
public class LawsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * laws
     */
    @ExcelProperty(value = "laws")
    private Long id;

    /**
     * laws
     */
    @ExcelProperty(value = "laws")
    private Date publishDate;

    /**
     * laws
     */
    @ExcelProperty(value = "laws")
    private String fileName;

    /**
     * laws
     */
    @ExcelProperty(value = "laws")
    private String orgCode;

    /**
     * laws
     */
    @ExcelProperty(value = "laws")
    private String orgName;

    /**
     * laws
     */
    @ExcelProperty(value = "laws", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
