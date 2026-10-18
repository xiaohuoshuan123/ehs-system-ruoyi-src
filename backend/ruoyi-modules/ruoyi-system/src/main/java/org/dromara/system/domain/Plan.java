package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Plan对象 plan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("plan")
public class Plan extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * plan
     */
    private Long id;

    /**
     * plan
     */
    private String orgCode;

    /**
     * plan
     */
    private String orgCodePath;

    /**
     * plan
     */
    private String planYear;

    /**
     * plan
     */
    private String targetType;

    /**
     * plan
     */
    private String treeData;

    /**
     * plan
     */
    private String planContent;

    /**
     * plan
     */
    private String status;


}
