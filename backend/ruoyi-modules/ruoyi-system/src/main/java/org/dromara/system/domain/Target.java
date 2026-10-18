package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Target对象 target
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("target")
public class Target extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * target
     */
    private Long id;

    /**
     * target
     */
    private String targetYear;

    /**
     * target
     */
    private String orgCodePath;

    /**
     * target
     */
    private String orgCode;

    /**
     * target
     */
    private String targetName;

    /**
     * target
     */
    private String targetContent;

    /**
     * target
     */
    private String targetType;

    /**
     * target
     */
    private Long parentId;

    /**
     * target
     */
    private String treeData;

    /**
     * target
     */
    private String status;


}
