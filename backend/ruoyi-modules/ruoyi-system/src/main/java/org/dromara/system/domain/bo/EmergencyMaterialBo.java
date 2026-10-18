package org.dromara.system.domain.bo;

import org.dromara.system.domain.EmergencyMaterial;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * EmergencyMaterial业务对象 emergency_material
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = EmergencyMaterial.class, reverseConvertGenerate = false)
public class EmergencyMaterialBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_material
     */
    @NotNull(message = "emergency_material不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * emergency_material
     */
    @NotBlank(message = "emergency_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodeId;

    /**
     * emergency_material
     */
    @NotBlank(message = "emergency_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String materialName;

    /**
     * emergency_material
     */
    @NotBlank(message = "emergency_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String materialType;

    /**
     * emergency_material
     */
    @NotNull(message = "emergency_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer materialQuantity;

    /**
     * emergency_material
     */
    @NotBlank(message = "emergency_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String materialLocation;

    /**
     * emergency_material
     */
    @NotNull(message = "emergency_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date checkDate;

    /**
     * emergency_material
     */
    @NotBlank(message = "emergency_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
