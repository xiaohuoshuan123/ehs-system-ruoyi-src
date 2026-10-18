package org.dromara.system.domain.bo;

import org.dromara.system.domain.DocumentProject;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * DocumentProject业务对象 document_project
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@AutoMapper(target = DocumentProject.class, reverseConvertGenerate = false)
public class DocumentProjectBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * document_project
     */
    @NotNull(message = "document_project不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * document_project
     */
    @NotBlank(message = "document_project不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dutyOrgCode;

    /**
     * document_project
     */
    @NotBlank(message = "document_project不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectName;

    /**
     * document_project
     */
    @NotBlank(message = "document_project不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectDesc;

    /**
     * document_project
     */
    @NotBlank(message = "document_project不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
