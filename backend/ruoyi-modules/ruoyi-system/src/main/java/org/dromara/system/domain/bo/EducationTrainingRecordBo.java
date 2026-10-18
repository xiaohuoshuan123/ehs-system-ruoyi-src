package org.dromara.system.domain.bo;

import org.dromara.system.domain.EducationTrainingRecord;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * EducationTrainingRecord业务对象 education_training_record
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@AutoMapper(target = EducationTrainingRecord.class, reverseConvertGenerate = false)
public class EducationTrainingRecordBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * education_training_record
     */
    @NotNull(message = "education_training_record不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * education_training_record
     */
    @NotNull(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long mstId;

    /**
     * education_training_record
     */
    @NotBlank(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode1;

    /**
     * education_training_record
     */
    @NotBlank(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainYear;

    /**
     * education_training_record
     */
    @NotBlank(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainType;

    /**
     * education_training_record
     */
    @NotBlank(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empCode;

    /**
     * education_training_record
     */
    @NotBlank(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * education_training_record
     */
    @NotBlank(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainResult;

    /**
     * education_training_record
     */
    @NotNull(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal score;

    /**
     * education_training_record
     */
    @NotBlank(message = "education_training_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
