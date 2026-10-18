package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * KeyPosition对象 key_position
 *
 * @author Hermes
 * @date 2026-10-17 05:32:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("key_position")
public class KeyPosition extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * key_position
     */
    private Long id;

    /**
     * key_position
     */
    private String positionTitle;

    /**
     * key_position
     */
    private String specificLocation;

    /**
     * key_position
     */
    private String orgCodePath;

    /**
     * key_position
     */
    private String dutyContent;

    /**
     * key_position
     */
    private String emergencyProcedure;

    /**
     * key_position
     */
    private String status;


}
