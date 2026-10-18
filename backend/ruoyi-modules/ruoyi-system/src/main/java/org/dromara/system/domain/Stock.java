package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * Stock对象 stock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("stock")
public class Stock extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * stock
     */
    private Long id;

    /**
     * stock
     */
    private String storageName;

    /**
     * stock
     */
    private String articleName;

    /**
     * stock
     */
    private String articleCode;

    /**
     * stock
     */
    private BigDecimal stockQuantity;

    /**
     * stock
     */
    private BigDecimal safetyStock;

    /**
     * stock
     */
    private String status;


}
