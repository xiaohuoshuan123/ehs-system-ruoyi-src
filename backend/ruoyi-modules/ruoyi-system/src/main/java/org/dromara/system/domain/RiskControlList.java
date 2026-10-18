package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * RiskControlList对象 risk_control_list
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("risk_control_list")
public class RiskControlList extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * risk_control_list
     */
    private Long id;

    /**
     * risk_control_list
     */
    private String orgRecFlag;

    /**
     * risk_control_list
     */
    private Long workunitId;

    /**
     * risk_control_list
     */
    private String riskName;

    /**
     * risk_control_list
     */
    private String riskLevel;

    /**
     * risk_control_list
     */
    private String controlMeasure;

    /**
     * risk_control_list
     */
    private String status;


}
