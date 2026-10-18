package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Msds对象 msds
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("msds")
public class Msds extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * msds
     */
    private Long id;

    /**
     * msds
     */
    private String articleCode;

    /**
     * msds
     */
    private String articleName;

    /**
     * msds
     */
    private String issueOrgCode;

    /**
     * msds
     */
    private String msdsContent;

    /**
     * msds
     */
    private String attachmentIds;

    /**
     * msds
     */
    private String status;


}
