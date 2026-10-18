package org.dromara.system.domain.vo;

import org.dromara.system.domain.MasterApprentice;
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
 * MasterApprentice视图对象 master_apprentice
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MasterApprentice.class)
public class MasterApprenticeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private Long id;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private String masterName;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private String masterCode;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private String apprenticeName;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private String apprenticeCode;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private String masterOrgCodePath;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private Date startDate;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice")
    private Date endDate;

    /**
     * master_apprentice
     */
    @ExcelProperty(value = "master_apprentice", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
