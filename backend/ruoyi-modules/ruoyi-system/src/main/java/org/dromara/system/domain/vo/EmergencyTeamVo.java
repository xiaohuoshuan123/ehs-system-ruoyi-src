package org.dromara.system.domain.vo;

import org.dromara.system.domain.EmergencyTeam;
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

/**
 * EmergencyTeam视图对象 emergency_team
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EmergencyTeam.class)
public class EmergencyTeamVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * emergency_team
     */
    @ExcelProperty(value = "emergency_team")
    private Long id;

    /**
     * emergency_team
     */
    @ExcelProperty(value = "emergency_team")
    private String orgCodeId;

    /**
     * emergency_team
     */
    @ExcelProperty(value = "emergency_team")
    private String teamName;

    /**
     * emergency_team
     */
    @ExcelProperty(value = "emergency_team", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
