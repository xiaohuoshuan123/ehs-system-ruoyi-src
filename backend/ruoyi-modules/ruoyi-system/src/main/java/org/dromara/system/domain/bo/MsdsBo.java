package org.dromara.system.domain.bo;

import org.dromara.system.domain.Msds;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * Msds业务对象 msds
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = Msds.class, reverseConvertGenerate = false)
public class MsdsBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * msds
     */
    @NotNull(message = "msds不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * msds
     */
    @NotBlank(message = "msds不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleCode;

    /**
     * msds
     */
    @NotBlank(message = "msds不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleName;

    /**
     * msds
     */
    @NotBlank(message = "msds不能为空", groups = { AddGroup.class, EditGroup.class })
    private String issueOrgCode;

    /**
     * msds
     */
    @NotBlank(message = "msds不能为空", groups = { AddGroup.class, EditGroup.class })
    private String msdsContent;

    /**
     * msds
     */
    @NotBlank(message = "msds不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attachmentIds;

    /**
     * msds
     */
    @NotBlank(message = "msds不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
