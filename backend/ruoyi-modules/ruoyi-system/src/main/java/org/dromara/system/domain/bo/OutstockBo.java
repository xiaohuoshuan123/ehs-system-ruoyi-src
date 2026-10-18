package org.dromara.system.domain.bo;

import org.dromara.system.domain.Outstock;
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
 * Outstock业务对象 outstock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = Outstock.class, reverseConvertGenerate = false)
public class OutstockBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * outstock
     */
    @NotNull(message = "outstock不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * outstock
     */
    @NotBlank(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;

    /**
     * outstock
     */
    @NotBlank(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outstockStatus;

    /**
     * outstock
     */
    @NotBlank(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleCode;

    /**
     * outstock
     */
    @NotBlank(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleName;

    /**
     * outstock
     */
    @NotBlank(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageName;

    /**
     * outstock
     */
    @NotNull(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal outquantity;

    /**
     * outstock
     */
    @NotNull(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date outTime;

    /**
     * outstock
     */
    @NotBlank(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String operator;

    /**
     * outstock
     */
    @NotBlank(message = "outstock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
