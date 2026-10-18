package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;
import java.util.Date;

/**
 * Instock对象 instock
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("instock")
public class Instock extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * instock
     */
    private Long id;

    /**
     * instock
     */
    private String state;

    /**
     * instock
     */
    private String articleCode;

    /**
     * instock
     */
    private String articleName;

    /**
     * instock
     */
    private String storageName;

    /**
     * instock
     */
    private BigDecimal inquantity;

    /**
     * instock
     */
    private Date inTime;

    /**
     * instock
     */
    private String operator;

    /**
     * instock
     */
    private String status;


}
