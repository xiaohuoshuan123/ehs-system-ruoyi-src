package org.dromara.system.domain.vo;

import org.dromara.system.domain.RiskControlList;
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
 * RiskControlList视图对象 risk_control_list
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = RiskControlList.class)
public class RiskControlListVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * risk_control_list
     */
    @ExcelProperty(value = "risk_control_list")
    private Long id;

    /**
     * risk_control_list
     */
    @ExcelProperty(value = "risk_control_list")
    private String riskName;

    /**
     * risk_control_list
     */
    @ExcelProperty(value = "risk_control_list", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
