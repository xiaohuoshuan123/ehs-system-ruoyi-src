package org.dromara.system.domain.bo;

import org.dromara.system.domain.Instock;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Instock业务对象 instock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = Instock.class, reverseConvertGenerate = false)
public class InstockBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * instock
     */
    @NotNull(message = "instock不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * instock
     */
    @NotBlank(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;

    /**
     * instock
     */
    @NotBlank(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleCode;

    /**
     * instock
     */
    @NotBlank(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleName;

    /**
     * instock
     */
    @NotBlank(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageName;

    /**
     * instock
     */
    @NotNull(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal inquantity;

    /**
     * instock
     */
    @NotNull(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date inTime;

    /**
     * instock
     */
    @NotBlank(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String operator;

    /**
     * instock
     */
    @NotBlank(message = "instock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
