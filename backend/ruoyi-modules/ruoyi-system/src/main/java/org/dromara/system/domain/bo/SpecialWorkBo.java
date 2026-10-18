package org.dromara.system.domain.bo;

import org.dromara.system.domain.SpecialWork;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * SpecialWork业务对象 special_work
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = SpecialWork.class, reverseConvertGenerate = false)
public class SpecialWorkBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * special_work
     */
    @NotNull(message = "special_work不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * special_work
     */
    @NotBlank(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empCode;

    /**
     * special_work
     */
    @NotBlank(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * special_work
     */
    @NotBlank(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private String certificateClassValue;

    /**
     * special_work
     */
    @NotBlank(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private String certificateNo;

    /**
     * special_work
     */
    @NotBlank(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * special_work
     */
    @NotNull(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date issueDate;

    /**
     * special_work
     */
    @NotNull(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expireDate;

    /**
     * special_work
     */
    @NotBlank(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private String flagStatus;

    /**
     * special_work
     */
    @NotBlank(message = "special_work不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
