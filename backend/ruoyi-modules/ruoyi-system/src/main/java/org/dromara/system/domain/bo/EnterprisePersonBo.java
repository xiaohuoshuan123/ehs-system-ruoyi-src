package org.dromara.system.domain.bo;

import org.dromara.system.domain.EnterprisePerson;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * EnterprisePerson业务对象 enterprise_person
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = EnterprisePerson.class, reverseConvertGenerate = false)
public class EnterprisePersonBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * enterprise_person
     */
    @NotNull(message = "enterprise_person不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empCode;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String certificateTypeValue;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String certificateNo;

    /**
     * enterprise_person
     */
    @NotNull(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date issueDate;

    /**
     * enterprise_person
     */
    @NotNull(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expireDate;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String flagStatus;

    /**
     * enterprise_person
     */
    @NotBlank(message = "enterprise_person不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
