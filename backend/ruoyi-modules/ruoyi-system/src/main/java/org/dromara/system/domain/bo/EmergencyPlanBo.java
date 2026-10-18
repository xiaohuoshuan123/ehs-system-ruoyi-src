package org.dromara.system.domain.bo;

import org.dromara.system.domain.EmergencyPlan;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * EmergencyPlan业务对象 emergency_plan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = EmergencyPlan.class, reverseConvertGenerate = false)
public class EmergencyPlanBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_plan
     */
    @NotNull(message = "emergency_plan不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emerName;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodes;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emerType;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emergencyGrade;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emerContent;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emerLeader;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emerPhone;

    /**
     * emergency_plan
     */
    @NotNull(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date reviewDate;

    /**
     * emergency_plan
     */
    @NotNull(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date effectiveDate;

    /**
     * emergency_plan
     */
    @NotBlank(message = "emergency_plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
