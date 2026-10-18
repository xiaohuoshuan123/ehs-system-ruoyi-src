package org.dromara.system.domain.vo;

import org.dromara.system.domain.PatrolTask;
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
 * PatrolTask视图对象 patrol_task
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PatrolTask.class)
public class PatrolTaskVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * patrol_task
     */
    @ExcelProperty(value = "patrol_task")
    private Long id;

    /**
     * patrol_task
     */
    @ExcelProperty(value = "patrol_task")
    private String checkOrgCodePath;

    /**
     * patrol_task
     */
    @ExcelProperty(value = "patrol_task")
    private String taskName;

    /**
     * patrol_task
     */
    @ExcelProperty(value = "patrol_task")
    private Date taskTime;

    /**
     * patrol_task
     */
    @ExcelProperty(value = "patrol_task")
    private String checkerName;

    /**
     * patrol_task
     */
    @ExcelProperty(value = "patrol_task", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
