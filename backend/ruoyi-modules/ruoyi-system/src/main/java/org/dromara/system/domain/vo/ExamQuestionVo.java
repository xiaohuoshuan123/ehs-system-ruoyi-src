package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.ExamQuestion;
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
 * ExamQuestion视图对象 exam_question
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ExamQuestion.class)
public class ExamQuestionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * exam_question
     */
    @ExcelProperty(value = "exam_question")
    private Long id;

    /**
     * exam_question
     */
    @ExcelProperty(value = "exam_question", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
