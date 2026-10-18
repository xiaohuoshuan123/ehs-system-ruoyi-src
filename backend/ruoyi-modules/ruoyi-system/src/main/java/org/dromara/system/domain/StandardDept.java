package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * StandardDept对象 standard_dept
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("standard_dept")
public class StandardDept extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * standard_dept
     */
    private Long id;

    /**
     * standard_dept
     */
    private String keyword;

    /**
     * standard_dept
     */
    private String orgCodePath;

    /**
     * standard_dept
     */
    private String deptName;

    /**
     * standard_dept
     */
    private String deptCode;

    /**
     * standard_dept
     */
    private String status;


}
