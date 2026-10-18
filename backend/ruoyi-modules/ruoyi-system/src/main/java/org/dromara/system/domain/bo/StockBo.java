package org.dromara.system.domain.bo;

import org.dromara.system.domain.Stock;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Stock业务对象 stock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = Stock.class, reverseConvertGenerate = false)
public class StockBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * stock
     */
    @NotNull(message = "stock不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * stock
     */
    @NotBlank(message = "stock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageName;

    /**
     * stock
     */
    @NotBlank(message = "stock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleName;

    /**
     * stock
     */
    @NotBlank(message = "stock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String articleCode;

    /**
     * stock
     */
    @NotNull(message = "stock不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal stockQuantity;

    /**
     * stock
     */
    @NotNull(message = "stock不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal safetyStock;

    /**
     * stock
     */
    @NotBlank(message = "stock不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
