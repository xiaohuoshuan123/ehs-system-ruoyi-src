package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * EducationTrainingMst对象 education_training_mst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("education_training_mst")
public class EducationTrainingMst extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * education_training_mst
     */
    private Long id;

    /**
     * education_training_mst
     */
    private String trainYear;

    /**
     * education_training_mst
     */
    private String orgCode;

    /**
     * education_training_mst
     */
    private String orgCodePath;

    /**
     * education_training_mst
     */
    private String trainName;

    /**
     * education_training_mst
     */
    private String trainType;

    /**
     * education_training_mst
     */
    private String trainContent;

    /**
     * education_training_mst
     */
    private Date trainStartTime;

    /**
     * education_training_mst
     */
    private Date trainEndTime;

    /**
     * education_training_mst
     */
    private String trainer;

    /**
     * education_training_mst
     */
    private Integer participantCount;

    /**
     * education_training_mst
     */
    private String status;


}
