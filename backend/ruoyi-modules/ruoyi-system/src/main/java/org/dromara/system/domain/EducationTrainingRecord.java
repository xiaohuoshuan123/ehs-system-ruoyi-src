package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * EducationTrainingRecord对象 education_training_record
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("education_training_record")
public class EducationTrainingRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * education_training_record
     */
    private Long id;

    /**
     * education_training_record
     */
    private Long mstId;

    /**
     * education_training_record
     */
    private String orgCode1;

    /**
     * education_training_record
     */
    private String trainYear;

    /**
     * education_training_record
     */
    private String trainType;

    /**
     * education_training_record
     */
    private String empCode;

    /**
     * education_training_record
     */
    private String empName;

    /**
     * education_training_record
     */
    private String trainResult;

    /**
     * education_training_record
     */
    private BigDecimal score;

    /**
     * education_training_record
     */
    private String status;


}
