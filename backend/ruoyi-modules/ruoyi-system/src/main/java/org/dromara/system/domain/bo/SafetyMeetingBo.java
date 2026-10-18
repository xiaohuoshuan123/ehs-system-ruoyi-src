package org.dromara.system.domain.bo;

import org.dromara.system.domain.SafetyMeeting;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * SafetyMeeting业务对象 safety_meeting
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@AutoMapper(target = SafetyMeeting.class, reverseConvertGenerate = false)
public class SafetyMeetingBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_meeting
     */
    @NotNull(message = "safety_meeting不能为空", groups = { AddGroup.class })
    private Long id;

    /**
     * safety_meeting
     */
    @NotBlank(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private String meetTypeValue;

    /**
     * safety_meeting
     */
    @NotNull(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date checkTimeBegin;

    /**
     * safety_meeting
     */
    @NotNull(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date checkTimeEnd;

    /**
     * safety_meeting
     */
    @NotBlank(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgName;

    /**
     * safety_meeting
     */
    @NotBlank(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgCodePath;

    /**
     * safety_meeting
     */
    @NotBlank(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private String meetingTitle;

    /**
     * safety_meeting
     */
    @NotBlank(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private String meetingContent;

    /**
     * safety_meeting
     */
    @NotBlank(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attendees;

    /**
     * safety_meeting
     */
    @NotBlank(message = "safety_meeting不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
