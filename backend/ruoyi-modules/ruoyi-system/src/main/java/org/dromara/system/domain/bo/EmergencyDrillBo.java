package org.dromara.system.domain.bo;

import org.dromara.system.domain.EmergencyDrill;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * EmergencyDrill业务对象 emergency_drill
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = EmergencyDrill.class, reverseConvertGenerate = false)
public class EmergencyDrillBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_drill
     */
    @NotNull(message = "emergency_drill不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * emergency_drill
     */
    @NotBlank(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emergencyName;

    /**
     * emergency_drill
     */
    @NotBlank(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodes;

    /**
     * emergency_drill
     */
    @NotBlank(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private String drillType;

    /**
     * emergency_drill
     */
    @NotNull(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date drillTime;

    /**
     * emergency_drill
     */
    @NotBlank(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private String drillLocation;

    /**
     * emergency_drill
     */
    @NotBlank(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private String drillContent;

    /**
     * emergency_drill
     */
    @NotBlank(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private String drillResult;

    /**
     * emergency_drill
     */
    @NotBlank(message = "emergency_drill不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
