package org.dromara.system.domain.bo;

import org.dromara.system.domain.SignDispatch;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * SignDispatch业务对象 sign_dispatch
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = SignDispatch.class, reverseConvertGenerate = false)
public class SignDispatchBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * sign_dispatch
     */
    @NotNull(message = "sign_dispatch不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * sign_dispatch
     */
    @NotNull(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long templateId;

    /**
     * sign_dispatch
     */
    @NotBlank(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dispatchYear;

    /**
     * sign_dispatch
     */
    @NotNull(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long targetId;

    /**
     * sign_dispatch
     */
    @NotBlank(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * sign_dispatch
     */
    @NotNull(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long dictId;

    /**
     * sign_dispatch
     */
    @NotBlank(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dispatchStatus;

    /**
     * sign_dispatch
     */
    @NotBlank(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private String signStatus;

    /**
     * sign_dispatch
     */
    @NotBlank(message = "sign_dispatch不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
