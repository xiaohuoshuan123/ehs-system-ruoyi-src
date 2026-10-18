package org.dromara.system.domain.bo;

import org.dromara.system.domain.ExamQuestion;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * ExamQuestion业务对象 exam_question
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = ExamQuestion.class, reverseConvertGenerate = false)
public class ExamQuestionBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * exam_question
     */
    @NotNull(message = "exam_question不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankTitle;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankType;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String questionsType;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String questionStem;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String optionA;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String optionB;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String optionC;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String optionD;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String answer;

    /**
     * exam_question
     */
    @NotNull(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal score;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String analysis;

    /**
     * exam_question
     */
    @NotBlank(message = "exam_question不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
