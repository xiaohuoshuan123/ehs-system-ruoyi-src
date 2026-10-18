package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.ExamPaper;
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
 * ExamPaper视图对象 exam_paper
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ExamPaper.class)
public class ExamPaperVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * exam_paper
     */
    @ExcelProperty(value = "exam_paper")
    private Long id;

    /**
     * exam_paper
     */
    @ExcelProperty(value = "exam_paper")
    private String examName;

    /**
     * exam_paper
     */
    @ExcelProperty(value = "exam_paper", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
