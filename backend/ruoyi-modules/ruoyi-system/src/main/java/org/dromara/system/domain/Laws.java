package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * Laws对象 laws
 *
 * @author Hermes
 * @date 2026-10-17 05:32:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("laws")
public class Laws extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * laws
     */
    private Long id;

    /**
     * laws
     */
    private Date publishDate;

    /**
     * laws
     */
    private String fileNo;

    /**
     * laws
     */
    private String fileName;

    /**
     * laws
     */
    private String title;

    /**
     * laws
     */
    private String lawType;

    /**
     * laws
     */
    private String lawsDataStatus;

    /**
     * laws
     */
    private String effectStatus;

    /**
     * laws
     */
    private String orgCode;

    /**
     * laws
     */
    private String orgName;

    /**
     * laws
     */
    private String content;

    /**
     * laws
     */
    private String attachmentIds;

    /**
     * laws
     */
    private String status;


}
