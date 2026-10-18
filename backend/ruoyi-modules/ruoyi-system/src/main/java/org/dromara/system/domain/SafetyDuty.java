package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * SafetyDuty对象 safety_duty
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("safety_duty")
public class SafetyDuty extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_duty
     */
    private Long id;

    /**
     * safety_duty
     */
    private String orgCodePath;

    /**
     * safety_duty
     */
    private String orgCode;

    /**
     * safety_duty
     */
    private String orgName;

    /**
     * safety_duty
     */
    private String jobName;

    /**
     * safety_duty
     */
    private String title;

    /**
     * safety_duty
     */
    private String maintenance;

    /**
     * safety_duty
     */
    private String dutyContent;

    /**
     * safety_duty
     */
    private String status;


}
