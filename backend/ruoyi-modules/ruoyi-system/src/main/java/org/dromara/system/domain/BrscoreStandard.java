package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * BrscoreStandard对象 brscore_standard
 *
 * @author Hermes
 * @date 2026-10-17 05:32:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("brscore_standard")
public class BrscoreStandard extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * brscore_standard
     */
    private Long id;

    /**
     * brscore_standard
     */
    private String standardContent;

    /**
     * brscore_standard
     */
    private String orgCode;

    /**
     * brscore_standard
     */
    private String orgCodePath;

    /**
     * brscore_standard
     */
    private String status;


}
