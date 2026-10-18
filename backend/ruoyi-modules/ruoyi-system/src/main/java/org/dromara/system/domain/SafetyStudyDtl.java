package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;
import java.util.Date;

/**
 * SafetyStudyDtl对象 safety_study_dtl
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("safety_study_dtl")
public class SafetyStudyDtl extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_study_dtl
     */
    private Long id;

    /**
     * safety_study_dtl
     */
    private Long studyMstId;

    /**
     * safety_study_dtl
     */
    private String empCode;

    /**
     * safety_study_dtl
     */
    private String empName;

    /**
     * safety_study_dtl
     */
    private String studyStatus;

    /**
     * safety_study_dtl
     */
    private BigDecimal studyHours;

    /**
     * safety_study_dtl
     */
    private Date studyTime;


}
