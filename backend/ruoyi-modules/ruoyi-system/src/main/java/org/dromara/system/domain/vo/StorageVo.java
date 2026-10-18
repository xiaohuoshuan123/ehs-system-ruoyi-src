package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.Storage;
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
 * Storage视图对象 storage
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Storage.class)
public class StorageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * storage
     */
    @ExcelProperty(value = "storage")
    private Long id;

    /**
     * storage
     */
    @ExcelProperty(value = "storage")
    private String storageName;

    /**
     * storage
     */
    @ExcelProperty(value = "storage")
    private String storageCode;

    /**
     * storage
     */
    @ExcelProperty(value = "storage", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
