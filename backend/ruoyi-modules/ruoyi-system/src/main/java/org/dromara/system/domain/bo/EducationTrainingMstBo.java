package org.dromara.system.domain.bo;

import org.dromara.system.domain.EducationTrainingMst;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * EducationTrainingMst业务对象 education_training_mst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = EducationTrainingMst.class, reverseConvertGenerate = false)
public class EducationTrainingMstBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * education_training_mst
     */
    @NotNull(message = "education_training_mst不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainYear;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainName;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainType;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainContent;

    /**
     * education_training_mst
     */
    @NotNull(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date trainStartTime;

    /**
     * education_training_mst
     */
    @NotNull(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date trainEndTime;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String trainer;

    /**
     * education_training_mst
     */
    @NotNull(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer participantCount;

    /**
     * education_training_mst
     */
    @NotBlank(message = "education_training_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
