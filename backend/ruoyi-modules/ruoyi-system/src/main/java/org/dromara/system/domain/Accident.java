package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;
import java.util.Date;

/**
 * Accident对象 accident
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("accident")
public class Accident extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * accident
     */
    private Long id;

    /**
     * accident
     */
    private String theirOrgCode;

    /**
     * accident
     */
    private String accidentName;

    /**
     * accident
     */
    private Date accidentTime;

    /**
     * accident
     */
    private String accidentType;

    /**
     * accident
     */
    private String accidentDesc;

    /**
     * accident
     */
    private BigDecimal accidentLoss;

    /**
     * accident
     */
    private String status;


}
