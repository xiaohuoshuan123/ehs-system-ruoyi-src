package org.dromara.system.domain.bo;

import org.dromara.system.domain.SafetyCost;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * SafetyCost业务对象 safety_cost
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = SafetyCost.class, reverseConvertGenerate = false)
public class SafetyCostBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_cost
     */
    @NotNull(message = "safety_cost不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * safety_cost
     */
    @NotNull(message = "safety_cost不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long safetyCostId;

    /**
     * safety_cost
     */
    @NotBlank(message = "safety_cost不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * safety_cost
     */
    @NotBlank(message = "safety_cost不能为空", groups = { AddGroup.class, EditGroup.class })
    private String costName;

    /**
     * safety_cost
     */
    @NotNull(message = "safety_cost不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal costAmount;

    /**
     * safety_cost
     */
    @NotBlank(message = "safety_cost不能为空", groups = { AddGroup.class, EditGroup.class })
    private String costYear;

    /**
     * safety_cost
     */
    @NotBlank(message = "safety_cost不能为空", groups = { AddGroup.class, EditGroup.class })
    private String costMonth;

    /**
     * safety_cost
     */
    @NotBlank(message = "safety_cost不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
