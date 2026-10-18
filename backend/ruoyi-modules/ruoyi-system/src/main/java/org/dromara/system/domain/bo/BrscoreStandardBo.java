package org.dromara.system.domain.bo;

import org.dromara.system.domain.BrscoreStandard;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * BrscoreStandard业务对象 brscore_standard
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = BrscoreStandard.class, reverseConvertGenerate = false)
public class BrscoreStandardBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * brscore_standard
     */
    @NotNull(message = "brscore_standard不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * brscore_standard
     */
    @NotBlank(message = "brscore_standard不能为空", groups = { AddGroup.class, EditGroup.class })
    private String standardContent;

    /**
     * brscore_standard
     */
    @NotBlank(message = "brscore_standard不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * brscore_standard
     */
    @NotBlank(message = "brscore_standard不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * brscore_standard
     */
    @NotBlank(message = "brscore_standard不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
