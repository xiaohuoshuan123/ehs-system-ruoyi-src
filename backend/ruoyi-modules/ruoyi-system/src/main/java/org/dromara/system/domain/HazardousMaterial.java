package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * HazardousMaterial对象 hazardous_material
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hazardous_material")
public class HazardousMaterial extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * hazardous_material
     */
    private Long id;

    /**
     * hazardous_material
     */
    private String type;

    /**
     * hazardous_material
     */
    private String articleCode;

    /**
     * hazardous_material
     */
    private String articleName;

    /**
     * hazardous_material
     */
    private String iifSuperviseChemicals;

    /**
     * hazardous_material
     */
    private String orgCodePath;

    /**
     * hazardous_material
     */
    private String storageLocation;

    /**
     * hazardous_material
     */
    private BigDecimal quantity;

    /**
     * hazardous_material
     */
    private String unit;

    /**
     * hazardous_material
     */
    private String status;


}
