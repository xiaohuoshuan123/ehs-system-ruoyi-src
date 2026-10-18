package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * EmergencyPlan对象 emergency_plan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("emergency_plan")
public class EmergencyPlan extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_plan
     */
    private Long id;

    /**
     * emergency_plan
     */
    private String emerName;

    /**
     * emergency_plan
     */
    private String orgCodes;

    /**
     * emergency_plan
     */
    private String emerType;

    /**
     * emergency_plan
     */
    private String emergencyGrade;

    /**
     * emergency_plan
     */
    private String emerContent;

    /**
     * emergency_plan
     */
    private String emerLeader;

    /**
     * emergency_plan
     */
    private String emerPhone;

    /**
     * emergency_plan
     */
    private Date reviewDate;

    /**
     * emergency_plan
     */
    private Date effectiveDate;

    /**
     * emergency_plan
     */
    private String status;


}
