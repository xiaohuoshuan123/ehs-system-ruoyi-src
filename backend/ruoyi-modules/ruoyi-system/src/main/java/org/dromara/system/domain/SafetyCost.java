package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * SafetyCost对象 safety_cost
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("safety_cost")
public class SafetyCost extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_cost
     */
    private Long id;

    /**
     * safety_cost
     */
    private Long safetyCostId;

    /**
     * safety_cost
     */
    private String orgCodePath;

    /**
     * safety_cost
     */
    private String costName;

    /**
     * safety_cost
     */
    private BigDecimal costAmount;

    /**
     * safety_cost
     */
    private String costYear;

    /**
     * safety_cost
     */
    private String costMonth;

    /**
     * safety_cost
     */
    private String status;


}
