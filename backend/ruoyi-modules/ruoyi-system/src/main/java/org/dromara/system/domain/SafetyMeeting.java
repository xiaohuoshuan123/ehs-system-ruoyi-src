package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * SafetyMeeting对象 safety_meeting
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("safety_meeting")
public class SafetyMeeting extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_meeting
     */
    private Long id;

    /**
     * safety_meeting
     */
    private String meetTypeValue;

    /**
     * safety_meeting
     */
    private Date checkTimeBegin;

    /**
     * safety_meeting
     */
    private Date checkTimeEnd;

    /**
     * safety_meeting
     */
    private String orgName;

    /**
     * safety_meeting
     */
    private String orgCodePath;

    /**
     * safety_meeting
     */
    private String meetingTitle;

    /**
     * safety_meeting
     */
    private String meetingContent;

    /**
     * safety_meeting
     */
    private String attendees;

    /**
     * safety_meeting
     */
    private String status;


}
