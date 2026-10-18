package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * Storage对象 storage
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("storage")
public class Storage extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * storage
     */
    private Long id;

    /**
     * storage
     */
    private String storageName;

    /**
     * storage
     */
    private String storageCode;

    /**
     * storage
     */
    private String storageLocation;

    /**
     * storage
     */
    private BigDecimal capacity;

    /**
     * storage
     */
    private String status;


}
