package org.dromara.system.domain.bo;

import org.dromara.system.domain.PositionConfigure;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * PositionConfigure业务对象 position_configure
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = PositionConfigure.class, reverseConvertGenerate = false)
public class PositionConfigureBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * position_configure
     */
    @NotNull(message = "position_configure不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * position_configure
     */
    @NotBlank(message = "position_configure不能为空", groups = { AddGroup.class, EditGroup.class })
    private String positionName;

    /**
     * position_configure
     */
    @NotBlank(message = "position_configure不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkOrgCodePath;

    /**
     * position_configure
     */
    @NotNull(message = "position_configure不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer checkCycle;

    /**
     * position_configure
     */
    @NotBlank(message = "position_configure不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkContent;

    /**
     * position_configure
     */
    @NotBlank(message = "position_configure不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
