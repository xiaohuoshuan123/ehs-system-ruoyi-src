package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * SignTemplate对象 sign_template
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sign_template")
public class SignTemplate extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * sign_template
     */
    private Long id;

    /**
     * sign_template
     */
    private String templateName;

    /**
     * sign_template
     */
    private String applyPosition;

    /**
     * sign_template
     */
    private String templateContent;

    /**
     * sign_template
     */
    private String status;


}
