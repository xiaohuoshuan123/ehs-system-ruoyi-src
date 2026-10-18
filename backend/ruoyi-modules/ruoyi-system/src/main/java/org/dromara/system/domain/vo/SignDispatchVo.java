package org.dromara.system.domain.vo;

import org.dromara.system.domain.SignDispatch;
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
 * SignDispatch视图对象 sign_dispatch
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SignDispatch.class)
public class SignDispatchVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * sign_dispatch
     */
    @ExcelProperty(value = "sign_dispatch")
    private Long id;

    /**
     * sign_dispatch
     */
    @ExcelProperty(value = "sign_dispatch")
    private String empName;

    /**
     * sign_dispatch
     */
    @ExcelProperty(value = "sign_dispatch", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
