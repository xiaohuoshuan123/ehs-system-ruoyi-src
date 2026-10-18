package org.dromara.system.domain.bo;

import org.dromara.system.domain.SafetyStudyDtl;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SafetyStudyDtl业务对象 safety_study_dtl
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = SafetyStudyDtl.class, reverseConvertGenerate = false)
public class SafetyStudyDtlBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_study_dtl
     */
    @NotNull(message = "safety_study_dtl不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * safety_study_dtl
     */
    @NotNull(message = "safety_study_dtl不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long studyMstId;

    /**
     * safety_study_dtl
     */
    @NotBlank(message = "safety_study_dtl不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empCode;

    /**
     * safety_study_dtl
     */
    @NotBlank(message = "safety_study_dtl不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * safety_study_dtl
     */
    @NotBlank(message = "safety_study_dtl不能为空", groups = { AddGroup.class, EditGroup.class })
    private String studyStatus;

    /**
     * safety_study_dtl
     */
    @NotNull(message = "safety_study_dtl不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal studyHours;

    /**
     * safety_study_dtl
     */
    @NotNull(message = "safety_study_dtl不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date studyTime;


}
