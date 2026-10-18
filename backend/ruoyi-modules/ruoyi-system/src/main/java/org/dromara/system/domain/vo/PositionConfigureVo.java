package org.dromara.system.domain.vo;

import org.dromara.system.domain.PositionConfigure;
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
 * PositionConfigure视图对象 position_configure
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PositionConfigure.class)
public class PositionConfigureVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * position_configure
     */
    @ExcelProperty(value = "position_configure")
    private Long id;

    /**
     * position_configure
     */
    @ExcelProperty(value = "position_configure")
    private String positionName;

    /**
     * position_configure
     */
    @ExcelProperty(value = "position_configure")
    private String checkOrgCodePath;

    /**
     * position_configure
     */
    @ExcelProperty(value = "position_configure", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
