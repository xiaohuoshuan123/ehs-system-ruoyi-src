package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * ExamPaper对象 exam_paper
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_paper")
public class ExamPaper extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * exam_paper
     */
    private Long id;

    /**
     * exam_paper
     */
    private String examName;

    /**
     * exam_paper
     */
    private String status;

    /**
     * exam_paper
     */
    private Integer makeUpCount;

    /**
     * exam_paper
     */
    private BigDecimal totalScore;

    /**
     * exam_paper
     */
    private BigDecimal passScore;

    /**
     * exam_paper
     */
    private Integer duration;


}
