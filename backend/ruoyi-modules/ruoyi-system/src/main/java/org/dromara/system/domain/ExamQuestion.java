package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * ExamQuestion对象 exam_question
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_question")
public class ExamQuestion extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * exam_question
     */
    private Long id;

    /**
     * exam_question
     */
    private String bankTitle;

    /**
     * exam_question
     */
    private String bankType;

    /**
     * exam_question
     */
    private String questionsType;

    /**
     * exam_question
     */
    private String questionStem;

    /**
     * exam_question
     */
    private String optionA;

    /**
     * exam_question
     */
    private String optionB;

    /**
     * exam_question
     */
    private String optionC;

    /**
     * exam_question
     */
    private String optionD;

    /**
     * exam_question
     */
    private String answer;

    /**
     * exam_question
     */
    private BigDecimal score;

    /**
     * exam_question
     */
    private String analysis;

    /**
     * exam_question
     */
    private String status;


}
