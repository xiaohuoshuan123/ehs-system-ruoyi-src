package org.dromara.system.domain.vo;

import org.dromara.system.domain.DocumentProject;
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
 * DocumentProject视图对象 document_project
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DocumentProject.class)
public class DocumentProjectVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * document_project
     */
    @ExcelProperty(value = "document_project")
    private Long id;

    /**
     * document_project
     */
    @ExcelProperty(value = "document_project")
    private String dutyOrgCode;

    /**
     * document_project
     */
    @ExcelProperty(value = "document_project")
    private String projectName;

    /**
     * document_project
     */
    @ExcelProperty(value = "document_project", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
