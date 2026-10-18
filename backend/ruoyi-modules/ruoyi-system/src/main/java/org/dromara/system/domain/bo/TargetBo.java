package org.dromara.system.domain.bo;

import org.dromara.system.domain.Target;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * Target业务对象 target
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = Target.class, reverseConvertGenerate = false)
public class TargetBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * target
     */
    @NotNull(message = "target不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetYear;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetName;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetContent;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetType;

    /**
     * target
     */
    @NotNull(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentId;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String treeData;

    /**
     * target
     */
    @NotBlank(message = "target不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
