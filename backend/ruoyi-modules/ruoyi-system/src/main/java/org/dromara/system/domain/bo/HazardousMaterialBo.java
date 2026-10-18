package org.dromara.system.domain.bo;

import org.dromara.system.domain.HazardousMaterial;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * HazardousMaterial业务对象 hazardous_material
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = HazardousMaterial.class, reverseConvertGenerate = false)
public class HazardousMaterialBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * hazardous_material
     */
    @NotNull(message = "hazardous_material不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleCode;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleName;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String iifSuperviseChemicals;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageLocation;

    /**
     * hazardous_material
     */
    @NotNull(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal quantity;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String unit;

    /**
     * hazardous_material
     */
    @NotBlank(message = "hazardous_material不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
