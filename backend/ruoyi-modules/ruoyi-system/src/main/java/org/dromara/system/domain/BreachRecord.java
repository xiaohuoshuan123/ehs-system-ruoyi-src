package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * BreachRecord对象 breach_record
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("breach_record")
public class BreachRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * breach_record
     */
    private Long id;

    /**
     * breach_record
     */
    private String checkOrgCode;

    /**
     * breach_record
     */
    private String checkOrgCodePath;

    /**
     * breach_record
     */
    private String breachEmp;

    /**
     * breach_record
     */
    private Date breachTime;

    /**
     * breach_record
     */
    private String breachContent;

    /**
     * breach_record
     */
    private String breachType;

    /**
     * breach_record
     */
    private String options;

    /**
     * breach_record
     */
    private String status;


}
