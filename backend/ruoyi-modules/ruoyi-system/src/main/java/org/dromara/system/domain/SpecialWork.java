package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * SpecialWork对象 special_work
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("special_work")
public class SpecialWork extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * special_work
     */
    private Long id;

    /**
     * special_work
     */
    private String empCode;

    /**
     * special_work
     */
    private String empName;

    /**
     * special_work
     */
    private String certificateClassValue;

    /**
     * special_work
     */
    private String certificateNo;

    /**
     * special_work
     */
    private String orgCodePath;

    /**
     * special_work
     */
    private Date issueDate;

    /**
     * special_work
     */
    private Date expireDate;

    /**
     * special_work
     */
    private String flagStatus;

    /**
     * special_work
     */
    private String status;


}
