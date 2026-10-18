package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Architecture对象 architecture
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("architecture")
public class Architecture extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * architecture
     */
    private Long id;

    /**
     * architecture
     */
    private String orgCodePath;

    /**
     * architecture
     */
    private String orgCode;

    /**
     * architecture
     */
    private String orgName;

    /**
     * architecture
     */
    private String parentCode;

    /**
     * architecture
     */
    private Integer level;

    /**
     * architecture
     */
    private Integer sortOrder;

    /**
     * architecture
     */
    private String status;


}
