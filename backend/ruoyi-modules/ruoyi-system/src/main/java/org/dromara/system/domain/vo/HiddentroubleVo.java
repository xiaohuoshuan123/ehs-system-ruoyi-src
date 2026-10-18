package org.dromara.system.domain.vo;

import java.math.BigDecimal;
import org.dromara.system.domain.Hiddentrouble;
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
 * Hiddentrouble视图对象 hiddentrouble
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Hiddentrouble.class)
public class HiddentroubleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private Long id;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private String hiddenTroubleTypeName;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private String hiddenTroubleRankName;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private String discoverOrgCode;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private String discoverOrgCodePath;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private String discoverUserCode;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private String discoverUserName;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private Date discoverTime;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private String hiddenTroubleName;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private Date submitTime;

    /**
     * hiddentrouble
     */
    @ExcelProperty(value = "hiddentrouble")
    private Date closeTime;


}
