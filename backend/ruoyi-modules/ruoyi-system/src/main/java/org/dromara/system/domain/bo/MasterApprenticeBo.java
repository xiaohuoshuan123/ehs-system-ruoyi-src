package org.dromara.system.domain.bo;

import org.dromara.system.domain.MasterApprentice;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * MasterApprentice业务对象 master_apprentice
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@AutoMapper(target = MasterApprentice.class, reverseConvertGenerate = false)
public class MasterApprenticeBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * master_apprentice
     */
    @NotNull(message = "master_apprentice不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * master_apprentice
     */
    @NotBlank(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private String masterName;

    /**
     * master_apprentice
     */
    @NotBlank(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private String masterCode;

    /**
     * master_apprentice
     */
    @NotBlank(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private String apprenticeName;

    /**
     * master_apprentice
     */
    @NotBlank(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private String apprenticeCode;

    /**
     * master_apprentice
     */
    @NotBlank(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private String masterOrgCodePath;

    /**
     * master_apprentice
     */
    @NotNull(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startDate;

    /**
     * master_apprentice
     */
    @NotNull(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endDate;

    /**
     * master_apprentice
     */
    @NotBlank(message = "master_apprentice不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
