package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;
import java.util.Date;

/**
 * Outstock对象 outstock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("outstock")
public class Outstock extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * outstock
     */
    private Long id;

    /**
     * outstock
     */
    private String state;

    /**
     * outstock
     */
    private String outstockStatus;

    /**
     * outstock
     */
    private String articleCode;

    /**
     * outstock
     */
    private String articleName;

    /**
     * outstock
     */
    private String storageName;

    /**
     * outstock
     */
    private BigDecimal outquantity;

    /**
     * outstock
     */
    private Date outTime;

    /**
     * outstock
     */
    private String operator;

    /**
     * outstock
     */
    private String status;


}
