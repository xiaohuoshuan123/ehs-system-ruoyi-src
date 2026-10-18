package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * PositionConfigure对象 position_configure
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("position_configure")
public class PositionConfigure extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * position_configure
     */
    private Long id;

    /**
     * position_configure
     */
    private String positionName;

    /**
     * position_configure
     */
    private String checkOrgCodePath;

    /**
     * position_configure
     */
    private Integer checkCycle;

    /**
     * position_configure
     */
    private String checkContent;

    /**
     * position_configure
     */
    private String status;


}
