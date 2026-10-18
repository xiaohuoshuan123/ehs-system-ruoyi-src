package org.dromara.system.domain.vo;

import org.dromara.system.domain.KeyPosition;
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
 * KeyPosition视图对象 key_position
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = KeyPosition.class)
public class KeyPositionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * key_position
     */
    @ExcelProperty(value = "key_position")
    private Long id;

    /**
     * key_position
     */
    @ExcelProperty(value = "key_position")
    private String orgCodePath;

    /**
     * key_position
     */
    @ExcelProperty(value = "key_position", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
