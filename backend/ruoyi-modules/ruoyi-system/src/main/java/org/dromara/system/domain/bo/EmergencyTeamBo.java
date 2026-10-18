package org.dromara.system.domain.bo;

import org.dromara.system.domain.EmergencyTeam;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * EmergencyTeam业务对象 emergency_team
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@AutoMapper(target = EmergencyTeam.class, reverseConvertGenerate = false)
public class EmergencyTeamBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_team
     */
    @NotNull(message = "emergency_team不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * emergency_team
     */
    @NotBlank(message = "emergency_team不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodeId;

    /**
     * emergency_team
     */
    @NotBlank(message = "emergency_team不能为空", groups = { AddGroup.class, EditGroup.class })
    private String teamName;

    /**
     * emergency_team
     */
    @NotBlank(message = "emergency_team不能为空", groups = { AddGroup.class, EditGroup.class })
    private String teamLeader;

    /**
     * emergency_team
     */
    @NotBlank(message = "emergency_team不能为空", groups = { AddGroup.class, EditGroup.class })
    private String teamPhone;

    /**
     * emergency_team
     */
    @NotBlank(message = "emergency_team不能为空", groups = { AddGroup.class, EditGroup.class })
    private String teamMembers;

    /**
     * emergency_team
     */
    @NotBlank(message = "emergency_team不能为空", groups = { AddGroup.class, EditGroup.class })
    private String teamType;

    /**
     * emergency_team
     */
    @NotBlank(message = "emergency_team不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
