package org.dromara.system.domain.vo;

import org.dromara.system.domain.EducationTrainingMst;
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
 * EducationTrainingMst视图对象 education_training_mst
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EducationTrainingMst.class)
public class EducationTrainingMstVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * education_training_mst
     */
    @ExcelProperty(value = "education_training_mst")
    private Long id;

    /**
     * education_training_mst
     */
    @ExcelProperty(value = "education_training_mst")
    private String orgCode;

    /**
     * education_training_mst
     */
    @ExcelProperty(value = "education_training_mst")
    private String orgCodePath;

    /**
     * education_training_mst
     */
    @ExcelProperty(value = "education_training_mst")
    private String trainName;

    /**
     * education_training_mst
     */
    @ExcelProperty(value = "education_training_mst")
    private Date trainStartTime;

    /**
     * education_training_mst
     */
    @ExcelProperty(value = "education_training_mst")
    private Date trainEndTime;

    /**
     * education_training_mst
     */
    @ExcelProperty(value = "education_training_mst", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
