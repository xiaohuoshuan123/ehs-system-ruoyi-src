package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * PatrolTask对象 patrol_task
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("patrol_task")
public class PatrolTask extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * patrol_task
     */
    private Long id;

    /**
     * patrol_task
     */
    private String taskStatus;

    /**
     * patrol_task
     */
    private String checkOrgCodePath;

    /**
     * patrol_task
     */
    private String taskName;

    /**
     * patrol_task
     */
    private Date taskTime;

    /**
     * patrol_task
     */
    private String checkerName;

    /**
     * patrol_task
     */
    private String result;

    /**
     * patrol_task
     */
    private String status;


}
