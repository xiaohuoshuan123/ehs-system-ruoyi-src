package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * EmergencyEquipment对象 emergency_equipment
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("emergency_equipment")
public class EmergencyEquipment extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_equipment
     */
    private Long id;

    /**
     * emergency_equipment
     */
    private String equipmentName;

    /**
     * emergency_equipment
     */
    private String fireControlType;

    /**
     * emergency_equipment
     */
    private String equipmentModel;

    /**
     * emergency_equipment
     */
    private String equipmentLocation;

    /**
     * emergency_equipment
     */
    private Integer checkCycle;

    /**
     * emergency_equipment
     */
    private Date lastCheckDate;

    /**
     * emergency_equipment
     */
    private Date nextCheckDate;

    /**
     * emergency_equipment
     */
    private String status;


}
