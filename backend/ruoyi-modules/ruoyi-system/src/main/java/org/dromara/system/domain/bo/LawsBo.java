package org.dromara.system.domain.bo;

import org.dromara.system.domain.Laws;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * Laws业务对象 laws
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@AutoMapper(target = Laws.class, reverseConvertGenerate = false)
public class LawsBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * laws
     */
    @NotNull(message = "laws不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * laws
     */
    @NotNull(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date publishDate;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileNo;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileName;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String lawType;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String lawsDataStatus;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String effectStatus;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCode;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgName;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attachmentIds;

    /**
     * laws
     */
    @NotBlank(message = "laws不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
