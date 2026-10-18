package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * EmergencyTeam对象 emergency_team
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("emergency_team")
public class EmergencyTeam extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_team
     */
    private Long id;

    /**
     * emergency_team
     */
    private String orgCodeId;

    /**
     * emergency_team
     */
    private String teamName;

    /**
     * emergency_team
     */
    private String teamLeader;

    /**
     * emergency_team
     */
    private String teamPhone;

    /**
     * emergency_team
     */
    private String teamMembers;

    /**
     * emergency_team
     */
    private String teamType;

    /**
     * emergency_team
     */
    private String status;


}
