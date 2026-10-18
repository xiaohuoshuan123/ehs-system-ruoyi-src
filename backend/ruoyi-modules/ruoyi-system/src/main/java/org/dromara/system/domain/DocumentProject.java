package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * DocumentProject对象 document_project
 *
 * @author Hermes
 * @date 2026-10-17 05:32:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("document_project")
public class DocumentProject extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * document_project
     */
    private Long id;

    /**
     * document_project
     */
    private String dutyOrgCode;

    /**
     * document_project
     */
    private String projectName;

    /**
     * document_project
     */
    private String projectDesc;

    /**
     * document_project
     */
    private String status;


}
