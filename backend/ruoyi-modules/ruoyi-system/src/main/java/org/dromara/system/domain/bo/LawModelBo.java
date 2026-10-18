package org.dromara.system.domain.bo;

import org.dromara.system.domain.LawModel;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * LawModel业务对象 law_model
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = LawModel.class, reverseConvertGenerate = false)
public class LawModelBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * law_model
     */
    @NotNull(message = "law_model不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * law_model
     */
    @NotBlank(message = "law_model不能为空", groups = { AddGroup.class, EditGroup.class })
    private String moduleValue;

    /**
     * law_model
     */
    @NotBlank(message = "law_model不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetCode;

    /**
     * law_model
     */
    @NotBlank(message = "law_model不能为空", groups = { AddGroup.class, EditGroup.class })
    private String secendModuleName;

    /**
     * law_model
     */
    @NotBlank(message = "law_model不能为空", groups = { AddGroup.class, EditGroup.class })
    private String modelContent;

    /**
     * law_model
     */
    @NotBlank(message = "law_model不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
