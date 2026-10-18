package org.dromara.system.domain.bo;

import org.dromara.system.domain.PatrolTask;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * PatrolTask业务对象 patrol_task
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = PatrolTask.class, reverseConvertGenerate = false)
public class PatrolTaskBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * patrol_task
     */
    @NotNull(message = "patrol_task不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * patrol_task
     */
    @NotBlank(message = "patrol_task不能为空", groups = { AddGroup.class, EditGroup.class })
    private String taskStatus;

    /**
     * patrol_task
     */
    @NotBlank(message = "patrol_task不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkOrgCodePath;

    /**
     * patrol_task
     */
    @NotBlank(message = "patrol_task不能为空", groups = { AddGroup.class, EditGroup.class })
    private String taskName;

    /**
     * patrol_task
     */
    @NotNull(message = "patrol_task不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date taskTime;

    /**
     * patrol_task
     */
    @NotBlank(message = "patrol_task不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkerName;

    /**
     * patrol_task
     */
    @NotBlank(message = "patrol_task不能为空", groups = { AddGroup.class, EditGroup.class })
    private String result;

    /**
     * patrol_task
     */
    @NotBlank(message = "patrol_task不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
