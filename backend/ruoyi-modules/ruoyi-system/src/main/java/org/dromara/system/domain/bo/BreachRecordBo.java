package org.dromara.system.domain.bo;

import org.dromara.system.domain.BreachRecord;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * BreachRecord业务对象 breach_record
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = BreachRecord.class, reverseConvertGenerate = false)
public class BreachRecordBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * breach_record
     */
    @NotNull(message = "breach_record不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * breach_record
     */
    @NotBlank(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkOrgCode;

    /**
     * breach_record
     */
    @NotBlank(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkOrgCodePath;

    /**
     * breach_record
     */
    @NotBlank(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String breachEmp;

    /**
     * breach_record
     */
    @NotNull(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date breachTime;

    /**
     * breach_record
     */
    @NotBlank(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String breachContent;

    /**
     * breach_record
     */
    @NotBlank(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String breachType;

    /**
     * breach_record
     */
    @NotBlank(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String options;

    /**
     * breach_record
     */
    @NotBlank(message = "breach_record不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
