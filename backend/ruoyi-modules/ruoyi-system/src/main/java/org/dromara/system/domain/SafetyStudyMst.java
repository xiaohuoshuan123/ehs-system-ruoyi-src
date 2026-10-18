package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;
import java.util.Date;

/**
 * SafetyStudyMst对象 safety_study_mst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("safety_study_mst")
public class SafetyStudyMst extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_study_mst
     */
    private Long id;

    /**
     * safety_study_mst
     */
    private String materialsTitle;

    /**
     * safety_study_mst
     */
    private String status;

    /**
     * safety_study_mst
     */
    private String ifMustLearn;

    /**
     * safety_study_mst
     */
    private BigDecimal studyHours;

    /**
     * safety_study_mst
     */
    private String publisher;

    /**
     * safety_study_mst
     */
    private Date publishTime;


}
