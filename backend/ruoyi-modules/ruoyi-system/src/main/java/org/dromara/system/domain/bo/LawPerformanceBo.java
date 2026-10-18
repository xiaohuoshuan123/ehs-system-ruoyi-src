package org.dromara.system.domain.bo;

import org.dromara.system.domain.LawPerformance;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * LawPerformance业务对象 law_performance
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@AutoMapper(target = LawPerformance.class, reverseConvertGenerate = false)
public class LawPerformanceBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * law_performance
     */
    @NotNull(message = "law_performance不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * law_performance
     */
    @NotBlank(message = "law_performance不能为空", groups = { AddGroup.class, EditGroup.class })
    private String radiolabel;

    /**
     * law_performance
     */
    @NotBlank(message = "law_performance不能为空", groups = { AddGroup.class, EditGroup.class })
    private String performanceStatus;

    /**
     * law_performance
     */
    @NotNull(message = "law_performance不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;

    /**
     * law_performance
     */
    @NotBlank(message = "law_performance不能为空", groups = { AddGroup.class, EditGroup.class })
    private String periods;

    /**
     * law_performance
     */
    @NotBlank(message = "law_performance不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * law_performance
     */
    @NotBlank(message = "law_performance不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
