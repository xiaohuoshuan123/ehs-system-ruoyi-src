package org.dromara.system.domain.bo;

import org.dromara.system.domain.SignTemplate;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * SignTemplate业务对象 sign_template
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = SignTemplate.class, reverseConvertGenerate = false)
public class SignTemplateBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * sign_template
     */
    @NotNull(message = "sign_template不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * sign_template
     */
    @NotBlank(message = "sign_template不能为空", groups = { AddGroup.class, EditGroup.class })
    private String templateName;

    /**
     * sign_template
     */
    @NotBlank(message = "sign_template不能为空", groups = { AddGroup.class, EditGroup.class })
    private String applyPosition;

    /**
     * sign_template
     */
    @NotBlank(message = "sign_template不能为空", groups = { AddGroup.class, EditGroup.class })
    private String templateContent;

    /**
     * sign_template
     */
    @NotBlank(message = "sign_template不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
