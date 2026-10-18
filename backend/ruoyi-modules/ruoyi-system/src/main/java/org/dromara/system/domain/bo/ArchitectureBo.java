package org.dromara.system.domain.bo;

import org.dromara.system.domain.Architecture;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * Architecture业务对象 architecture
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = Architecture.class, reverseConvertGenerate = false)
public class ArchitectureBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * architecture
     */
    @NotNull(message = "architecture不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * architecture
     */
    @NotBlank(message = "architecture不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * architecture
     */
    @NotBlank(message = "architecture不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * architecture
     */
    @NotBlank(message = "architecture不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgName;

    /**
     * architecture
     */
    @NotBlank(message = "architecture不能为空", groups = { AddGroup.class, EditGroup.class })
    private String parentCode;

    /**
     * architecture
     */
    @NotNull(message = "architecture不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer level;

    /**
     * architecture
     */
    @NotNull(message = "architecture不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer sortOrder;

    /**
     * architecture
     */
    @NotBlank(message = "architecture不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
