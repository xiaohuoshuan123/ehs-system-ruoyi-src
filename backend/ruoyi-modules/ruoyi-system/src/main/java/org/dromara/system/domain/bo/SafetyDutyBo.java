package org.dromara.system.domain.bo;

import org.dromara.system.domain.SafetyDuty;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * SafetyDuty业务对象 safety_duty
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Data
@AutoMapper(target = SafetyDuty.class, reverseConvertGenerate = false)
public class SafetyDutyBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_duty
     */
    @NotNull(message = "safety_duty不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgName;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jobName;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String maintenance;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dutyContent;

    /**
     * safety_duty
     */
    @NotBlank(message = "safety_duty不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
