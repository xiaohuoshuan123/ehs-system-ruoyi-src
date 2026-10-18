package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * LawPerformance对象 law_performance
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("law_performance")
public class LawPerformance extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * law_performance
     */
    private Long id;

    /**
     * law_performance
     */
    private String radiolabel;

    /**
     * law_performance
     */
    private String performanceStatus;

    /**
     * law_performance
     */
    private Date endTime;

    /**
     * law_performance
     */
    private String periods;

    /**
     * law_performance
     */
    private String orgCodePath;

    /**
     * law_performance
     */
    private String status;


}
