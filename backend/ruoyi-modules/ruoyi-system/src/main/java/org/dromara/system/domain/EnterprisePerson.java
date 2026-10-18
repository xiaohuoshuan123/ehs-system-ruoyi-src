package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * EnterprisePerson对象 enterprise_person
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("enterprise_person")
public class EnterprisePerson extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * enterprise_person
     */
    private Long id;

    /**
     * enterprise_person
     */
    private String empCode;

    /**
     * enterprise_person
     */
    private String empName;

    /**
     * enterprise_person
     */
    private String orgCodePath;

    /**
     * enterprise_person
     */
    private String orgCode;

    /**
     * enterprise_person
     */
    private String certificateTypeValue;

    /**
     * enterprise_person
     */
    private String certificateNo;

    /**
     * enterprise_person
     */
    private Date issueDate;

    /**
     * enterprise_person
     */
    private Date expireDate;

    /**
     * enterprise_person
     */
    private String flagStatus;

    /**
     * enterprise_person
     */
    private String status;


}
