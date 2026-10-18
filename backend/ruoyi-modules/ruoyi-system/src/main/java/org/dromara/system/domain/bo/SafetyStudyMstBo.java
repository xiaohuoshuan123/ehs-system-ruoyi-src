package org.dromara.system.domain.bo;

import org.dromara.system.domain.SafetyStudyMst;
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
 * SafetyStudyMst业务对象 safety_study_mst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = SafetyStudyMst.class, reverseConvertGenerate = false)
public class SafetyStudyMstBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_study_mst
     */
    @NotNull(message = "safety_study_mst不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * safety_study_mst
     */
    @NotBlank(message = "safety_study_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String materialsTitle;

    /**
     * safety_study_mst
     */
    @NotBlank(message = "safety_study_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * safety_study_mst
     */
    @NotBlank(message = "safety_study_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ifMustLearn;

    /**
     * safety_study_mst
     */
    @NotNull(message = "safety_study_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal studyHours;

    /**
     * safety_study_mst
     */
    @NotBlank(message = "safety_study_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private String publisher;

    /**
     * safety_study_mst
     */
    @NotNull(message = "safety_study_mst不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date publishTime;


}
