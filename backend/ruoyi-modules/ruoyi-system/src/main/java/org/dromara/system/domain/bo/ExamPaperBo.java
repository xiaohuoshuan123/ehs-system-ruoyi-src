package org.dromara.system.domain.bo;

import org.dromara.system.domain.ExamPaper;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * ExamPaper业务对象 exam_paper
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = ExamPaper.class, reverseConvertGenerate = false)
public class ExamPaperBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * exam_paper
     */
    @NotNull(message = "exam_paper不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * exam_paper
     */
    @NotBlank(message = "exam_paper不能为空", groups = { AddGroup.class, EditGroup.class })
    private String examName;

    /**
     * exam_paper
     */
    @NotBlank(message = "exam_paper不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * exam_paper
     */
    @NotNull(message = "exam_paper不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer makeUpCount;

    /**
     * exam_paper
     */
    @NotNull(message = "exam_paper不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal totalScore;

    /**
     * exam_paper
     */
    @NotNull(message = "exam_paper不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal passScore;

    /**
     * exam_paper
     */
    @NotNull(message = "exam_paper不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer duration;


}
