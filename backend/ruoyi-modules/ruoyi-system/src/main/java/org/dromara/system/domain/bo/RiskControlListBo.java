package org.dromara.system.domain.bo;

import org.dromara.system.domain.RiskControlList;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * RiskControlList业务对象 risk_control_list
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = RiskControlList.class, reverseConvertGenerate = false)
public class RiskControlListBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * risk_control_list
     */
    @NotNull(message = "risk_control_list不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * risk_control_list
     */
    @NotBlank(message = "risk_control_list不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgRecFlag;

    /**
     * risk_control_list
     */
    @NotNull(message = "risk_control_list不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long workunitId;

    /**
     * risk_control_list
     */
    @NotBlank(message = "risk_control_list不能为空", groups = { AddGroup.class, EditGroup.class })
    private String riskName;

    /**
     * risk_control_list
     */
    @NotBlank(message = "risk_control_list不能为空", groups = { AddGroup.class, EditGroup.class })
    private String riskLevel;

    /**
     * risk_control_list
     */
    @NotBlank(message = "risk_control_list不能为空", groups = { AddGroup.class, EditGroup.class })
    private String controlMeasure;

    /**
     * risk_control_list
     */
    @NotBlank(message = "risk_control_list不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
