package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * EmergencyDrill对象 emergency_drill
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("emergency_drill")
public class EmergencyDrill extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_drill
     */
    private Long id;

    /**
     * emergency_drill
     */
    private String emergencyName;

    /**
     * emergency_drill
     */
    private String orgCodes;

    /**
     * emergency_drill
     */
    private String drillType;

    /**
     * emergency_drill
     */
    private Date drillTime;

    /**
     * emergency_drill
     */
    private String drillLocation;

    /**
     * emergency_drill
     */
    private String drillContent;

    /**
     * emergency_drill
     */
    private String drillResult;

    /**
     * emergency_drill
     */
    private String status;


}
