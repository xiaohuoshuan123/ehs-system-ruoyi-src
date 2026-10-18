package org.dromara.system.domain.vo;

import org.dromara.system.domain.Plan;
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
 * Plan视图对象 plan
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Plan.class)
public class PlanVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * plan
     */
    @ExcelProperty(value = "plan")
    private Long id;

    /**
     * plan
     */
    @ExcelProperty(value = "plan")
    private String orgCode;

    /**
     * plan
     */
    @ExcelProperty(value = "plan")
    private String orgCodePath;

    /**
     * plan
     */
    @ExcelProperty(value = "plan", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
