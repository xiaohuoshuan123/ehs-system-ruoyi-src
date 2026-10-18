package org.dromara.system.domain.bo;

import org.dromara.system.domain.Storage;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Storage业务对象 storage
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = Storage.class, reverseConvertGenerate = false)
public class StorageBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * storage
     */
    @NotNull(message = "storage不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * storage
     */
    @NotBlank(message = "storage不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageName;

    /**
     * storage
     */
    @NotBlank(message = "storage不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageCode;

    /**
     * storage
     */
    @NotBlank(message = "storage不能为空", groups = { AddGroup.class, EditGroup.class })
    private String storageLocation;

    /**
     * storage
     */
    @NotNull(message = "storage不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal capacity;

    /**
     * storage
     */
    @NotBlank(message = "storage不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
