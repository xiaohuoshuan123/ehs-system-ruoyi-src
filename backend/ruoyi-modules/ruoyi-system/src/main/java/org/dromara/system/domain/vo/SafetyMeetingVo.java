package org.dromara.system.domain.vo;

import org.dromara.system.domain.SafetyMeeting;
import org.apache.fesod.sheet.annotation.ExcelIgnoreUnannotated;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * SafetyMeeting视图对象 safety_meeting
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SafetyMeeting.class)
public class SafetyMeetingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_meeting
     */
    @ExcelProperty(value = "safety_meeting")
    private Long id;

    /**
     * safety_meeting
     */
    @ExcelProperty(value = "safety_meeting")
    private Date checkTimeBegin;

    /**
     * safety_meeting
     */
    @ExcelProperty(value = "safety_meeting")
    private Date checkTimeEnd;

    /**
     * safety_meeting
     */
    @ExcelProperty(value = "safety_meeting")
    private String orgName;

    /**
     * safety_meeting
     */
    @ExcelProperty(value = "safety_meeting")
    private String orgCodePath;

    /**
     * safety_meeting
     */
    @ExcelProperty(value = "safety_meeting", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
