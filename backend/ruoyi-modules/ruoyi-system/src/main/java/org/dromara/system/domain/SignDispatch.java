package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * SignDispatch对象 sign_dispatch
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sign_dispatch")
public class SignDispatch extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * sign_dispatch
     */
    private Long id;

    /**
     * sign_dispatch
     */
    private Long templateId;

    /**
     * sign_dispatch
     */
    private String dispatchYear;

    /**
     * sign_dispatch
     */
    private Long targetId;

    /**
     * sign_dispatch
     */
    private String empName;

    /**
     * sign_dispatch
     */
    private Long dictId;

    /**
     * sign_dispatch
     */
    private String dispatchStatus;

    /**
     * sign_dispatch
     */
    private String signStatus;

    /**
     * sign_dispatch
     */
    private String status;


}
