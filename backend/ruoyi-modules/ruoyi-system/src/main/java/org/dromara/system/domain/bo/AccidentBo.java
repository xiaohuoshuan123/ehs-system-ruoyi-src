package org.dromara.system.domain.bo;

import org.dromara.system.domain.Accident;
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
 * Accident业务对象 accident
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = Accident.class, reverseConvertGenerate = false)
public class AccidentBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * accident
     */
    @NotNull(message = "accident不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * accident
     */
    @NotBlank(message = "accident不能为空", groups = { AddGroup.class, EditGroup.class })
    private String theirOrgCode;

    /**
     * accident
     */
    @NotBlank(message = "accident不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accidentName;

    /**
     * accident
     */
    @NotNull(message = "accident不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date accidentTime;

    /**
     * accident
     */
    @NotBlank(message = "accident不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accidentType;

    /**
     * accident
     */
    @NotBlank(message = "accident不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accidentDesc;

    /**
     * accident
     */
    @NotNull(message = "accident不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal accidentLoss;

    /**
     * accident
     */
    @NotBlank(message = "accident不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
