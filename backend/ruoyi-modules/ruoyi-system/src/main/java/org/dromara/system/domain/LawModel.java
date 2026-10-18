package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * LawModel对象 law_model
 *
 * @author Hermes
 * @date 2026-10-17 05:32:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("law_model")
public class LawModel extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * law_model
     */
    private Long id;

    /**
     * law_model
     */
    private String moduleValue;

    /**
     * law_model
     */
    private String targetCode;

    /**
     * law_model
     */
    private String secendModuleName;

    /**
     * law_model
     */
    private String modelContent;

    /**
     * law_model
     */
    private String status;


}
