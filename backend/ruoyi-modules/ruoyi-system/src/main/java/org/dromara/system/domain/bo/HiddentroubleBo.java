package org.dromara.system.domain.bo;

import org.dromara.system.domain.Hiddentrouble;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Hiddentrouble业务对象 hiddentrouble
 *
 * @author Hermes
 * @date 2026-10-17 05:32:51
 */
@Data
@AutoMapper(target = Hiddentrouble.class, reverseConvertGenerate = false)
public class HiddentroubleBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键（编辑时必填） */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /** 状态: 0暂存 1提交 2整改 3验证 4关闭 5撤回 */
    private String status;

    /** 操作: zc暂存 tj提交 */
    private String operate;

    /** 隐患类型名称 */
    private String hiddenTroubleTypeName;

    /** 隐患等级名称 */
    private String hiddenTroubleRankName;

    /** 发现部门编码 */
    private String discoverOrgCode;

    /** 发现部门编码路径 */
    private String discoverOrgCodePath;

    /** 发现人编码 */
    private String discoverUserCode;

    /** 发现人姓名 */
    private String discoverUserName;

    /** 发现时间 */
    private Date discoverTime;

    /** 隐患名称（必填） */
    @NotBlank(message = "隐患名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String hiddenTroubleName;

    /** 隐患描述 */
    private String hiddenTroubleDesc;

    /** 危险类型 */
    private String dangerType;

    /** 隐患位置 */
    private String location;

    /** 来源 */
    private String listsource;

    /** 是否部门发现标识 */
    private String unitFindFlag;

    /** 奖励金额 */
    private BigDecimal rewardXl;

    /** 排序号 */
    private Integer displayOrder;

    /** 提交时间 */
    private Date submitTime;

    /** 关闭时间 */
    private Date closeTime;

    /** 业务来源 */
    private String bizSource;

    /** 业务来源ID */
    private String bizSourceId;

    /** IEQMS状态 */
    private String ieqmsStatus;

    /** 用户岗位ID */
    private String userJobId;

    /** 删除标识 */
    private String deleteFlag;

}
