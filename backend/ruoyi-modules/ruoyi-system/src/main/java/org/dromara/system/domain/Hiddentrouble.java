package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;
import java.util.Date;

/**
 * Hiddentrouble对象 hiddentrouble
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hiddentrouble")
public class Hiddentrouble extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * hiddentrouble
     */
    private Long id;

    /**
     * hiddentrouble
     */
    private String status;

    /**
     * hiddentrouble
     */
    private String operate;

    /**
     * hiddentrouble
     */
    private String hiddenTroubleTypeName;

    /**
     * hiddentrouble
     */
    private String hiddenTroubleRankName;

    /**
     * hiddentrouble
     */
    private String discoverOrgCode;

    /**
     * hiddentrouble
     */
    private String discoverOrgCodePath;

    /**
     * hiddentrouble
     */
    private String discoverUserCode;

    /**
     * hiddentrouble
     */
    private String discoverUserName;

    /**
     * hiddentrouble
     */
    private Date discoverTime;

    /**
     * hiddentrouble
     */
    private String hiddenTroubleName;

    /**
     * hiddentrouble
     */
    private String hiddenTroubleDesc;

    /**
     * hiddentrouble
     */
    private String dangerType;

    /**
     * hiddentrouble
     */
    private String location;

    /**
     * hiddentrouble
     */
    private String listsource;

    /**
     * hiddentrouble
     */
    private String unitFindFlag;

    /**
     * hiddentrouble
     */
    private BigDecimal rewardXl;

    /**
     * hiddentrouble
     */
    private Integer displayOrder;

    /**
     * hiddentrouble
     */
    private Date submitTime;

    /**
     * hiddentrouble
     */
    private Date closeTime;

    /**
     * hiddentrouble
     */
    private String bizSource;

    /**
     * hiddentrouble
     */
    private String bizSourceId;

    /**
     * hiddentrouble
     */
    private String ieqmsStatus;

    /**
     * hiddentrouble
     */
    private String userJobId;

    /**
     * hiddentrouble
     */
    private String deleteFlag;


}
