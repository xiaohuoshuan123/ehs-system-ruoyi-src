package org.dromara.system.domain.bo;

import org.dromara.system.domain.EmergencyPoint;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * EmergencyPoint业务对象 emergency_point
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = EmergencyPoint.class, reverseConvertGenerate = false)
public class EmergencyPointBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_point
     */
    @NotNull(message = "emergency_point不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * emergency_point
     */
    @NotBlank(message = "emergency_point不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dutyOrgCode;

    /**
     * emergency_point
     */
    @NotBlank(message = "emergency_point不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pointCoordinate;

    /**
     * emergency_point
     */
    @NotBlank(message = "emergency_point不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pointName;

    /**
     * emergency_point
     */
    @NotBlank(message = "emergency_point不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pointType;

    /**
     * emergency_point
     */
    @NotBlank(message = "emergency_point不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pointContent;

    /**
     * emergency_point
     */
    @NotBlank(message = "emergency_point不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
