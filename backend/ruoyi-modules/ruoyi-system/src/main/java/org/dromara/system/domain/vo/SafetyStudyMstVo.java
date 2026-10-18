package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.SafetyStudyMst;
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
 * SafetyStudyMst视图对象 safety_study_mst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SafetyStudyMst.class)
public class SafetyStudyMstVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * safety_study_mst
     */
    @ExcelProperty(value = "safety_study_mst")
    private Long id;

    /**
     * safety_study_mst
     */
    @ExcelProperty(value = "safety_study_mst", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * safety_study_mst
     */
    @ExcelProperty(value = "safety_study_mst")
    private Date publishTime;


}
