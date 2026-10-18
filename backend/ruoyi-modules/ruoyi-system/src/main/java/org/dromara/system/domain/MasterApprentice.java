package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * MasterApprentice对象 master_apprentice
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("master_apprentice")
public class MasterApprentice extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * master_apprentice
     */
    private Long id;

    /**
     * master_apprentice
     */
    private String masterName;

    /**
     * master_apprentice
     */
    private String masterCode;

    /**
     * master_apprentice
     */
    private String apprenticeName;

    /**
     * master_apprentice
     */
    private String apprenticeCode;

    /**
     * master_apprentice
     */
    private String masterOrgCodePath;

    /**
     * master_apprentice
     */
    private Date startDate;

    /**
     * master_apprentice
     */
    private Date endDate;

    /**
     * master_apprentice
     */
    private String status;


}
