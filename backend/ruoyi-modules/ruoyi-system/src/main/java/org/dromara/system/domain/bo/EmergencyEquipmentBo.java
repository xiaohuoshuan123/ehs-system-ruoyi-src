package org.dromara.system.domain.bo;

import org.dromara.system.domain.EmergencyEquipment;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * EmergencyEquipment业务对象 emergency_equipment
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = EmergencyEquipment.class, reverseConvertGenerate = false)
public class EmergencyEquipmentBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_equipment
     */
    @NotNull(message = "emergency_equipment不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * emergency_equipment
     */
    @NotBlank(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private String equipmentName;

    /**
     * emergency_equipment
     */
    @NotBlank(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fireControlType;

    /**
     * emergency_equipment
     */
    @NotBlank(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private String equipmentModel;

    /**
     * emergency_equipment
     */
    @NotBlank(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private String equipmentLocation;

    /**
     * emergency_equipment
     */
    @NotNull(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer checkCycle;

    /**
     * emergency_equipment
     */
    @NotNull(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastCheckDate;

    /**
     * emergency_equipment
     */
    @NotNull(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date nextCheckDate;

    /**
     * emergency_equipment
     */
    @NotBlank(message = "emergency_equipment不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
