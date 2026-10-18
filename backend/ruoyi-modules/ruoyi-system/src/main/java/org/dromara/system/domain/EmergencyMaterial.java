package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * EmergencyMaterial对象 emergency_material
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("emergency_material")
public class EmergencyMaterial extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_material
     */
    private Long id;

    /**
     * emergency_material
     */
    private String orgCodeId;

    /**
     * emergency_material
     */
    private String materialName;

    /**
     * emergency_material
     */
    private String materialType;

    /**
     * emergency_material
     */
    private Integer materialQuantity;

    /**
     * emergency_material
     */
    private String materialLocation;

    /**
     * emergency_material
     */
    private Date checkDate;

    /**
     * emergency_material
     */
    private String status;


}
