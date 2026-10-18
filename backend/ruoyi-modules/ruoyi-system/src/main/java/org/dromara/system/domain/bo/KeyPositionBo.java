package org.dromara.system.domain.bo;

import org.dromara.system.domain.KeyPosition;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * KeyPosition业务对象 key_position
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = KeyPosition.class, reverseConvertGenerate = false)
public class KeyPositionBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * key_position
     */
    @NotNull(message = "key_position不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * key_position
     */
    @NotBlank(message = "key_position不能为空", groups = { AddGroup.class, EditGroup.class })
    private String positionTitle;

    /**
     * key_position
     */
    @NotBlank(message = "key_position不能为空", groups = { AddGroup.class, EditGroup.class })
    private String specificLocation;

    /**
     * key_position
     */
    @NotBlank(message = "key_position不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * key_position
     */
    @NotBlank(message = "key_position不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dutyContent;

    /**
     * key_position
     */
    @NotBlank(message = "key_position不能为空", groups = { AddGroup.class, EditGroup.class })
    private String emergencyProcedure;

    /**
     * key_position
     */
    @NotBlank(message = "key_position不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
