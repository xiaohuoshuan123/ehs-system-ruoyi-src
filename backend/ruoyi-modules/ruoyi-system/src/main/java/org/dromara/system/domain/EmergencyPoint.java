package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * EmergencyPoint对象 emergency_point
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("emergency_point")
public class EmergencyPoint extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_point
     */
    private Long id;

    /**
     * emergency_point
     */
    private String dutyOrgCode;

    /**
     * emergency_point
     */
    private String pointCoordinate;

    /**
     * emergency_point
     */
    private String pointName;

    /**
     * emergency_point
     */
    private String pointType;

    /**
     * emergency_point
     */
    private String pointContent;

    /**
     * emergency_point
     */
    private String status;


}
