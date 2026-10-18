package org.dromara.system.domain.bo;

import org.dromara.system.domain.Plan;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * Plan业务对象 plan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = Plan.class, reverseConvertGenerate = false)
public class PlanBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * plan
     */
    @NotNull(message = "plan不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * plan
     */
    @NotBlank(message = "plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * plan
     */
    @NotBlank(message = "plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * plan
     */
    @NotBlank(message = "plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String planYear;

    /**
     * plan
     */
    @NotBlank(message = "plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetType;

    /**
     * plan
     */
    @NotBlank(message = "plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String treeData;

    /**
     * plan
     */
    @NotBlank(message = "plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String planContent;

    /**
     * plan
     */
    @NotBlank(message = "plan不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
