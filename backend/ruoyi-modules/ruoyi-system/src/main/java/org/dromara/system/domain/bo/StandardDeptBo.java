package org.dromara.system.domain.bo;

import org.dromara.system.domain.StandardDept;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * StandardDept业务对象 standard_dept
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = StandardDept.class, reverseConvertGenerate = false)
public class StandardDeptBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * standard_dept
     */
    @NotNull(message = "standard_dept不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * standard_dept
     */
    @NotBlank(message = "standard_dept不能为空", groups = { AddGroup.class, EditGroup.class })
    private String keyword;

    /**
     * standard_dept
     */
    @NotBlank(message = "standard_dept不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * standard_dept
     */
    @NotBlank(message = "standard_dept不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deptName;

    /**
     * standard_dept
     */
    @NotBlank(message = "standard_dept不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deptCode;

    /**
     * standard_dept
     */
    @NotBlank(message = "standard_dept不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
