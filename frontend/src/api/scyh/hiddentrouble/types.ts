export interface HiddentroubleVO {
  /** 主键ID */
  id: string | number;
  /** 状态:0暂存1提交2整改3验证4关闭5撤回 */
  status: string;
  /** 隐患类型名称 */
  hiddenTroubleTypeName: string;
  /** 隐患等级 */
  hiddenTroubleRankName: string;
  /** 发现组织编码 */
  discoverOrgCode: string;
  /** 发现组织路径 */
  discoverOrgCodePath: string;
  /** 发现人编码 */
  discoverUserCode: string;
  /** 发现人姓名 */
  discoverUserName: string;
  /** 发现时间 */
  discoverTime: string;
  /** 隐患名称 */
  hiddenTroubleName: string;
  /** 隐患描述 */
  hiddenTroubleDesc?: string;
  /** 危险源类型 */
  dangerType?: string;
  /** 隐患位置 */
  location?: string;
  /** 来源 */
  listsource?: string;
  /** 提交时间 */
  submitTime: string;
  /** 关闭时间 */
  closeTime: string;
}

export interface HiddentroubleForm extends BaseEntity {
  /** 主键ID */
  id?: string | number;
  /** 状态:0暂存1提交2整改3验证4关闭5撤回 */
  status?: string;
  /** 操作:zc暂存tj提交 */
  operate?: string;
  /** 隐患类型名称 */
  hiddenTroubleTypeName?: string;
  /** 隐患等级 */
  hiddenTroubleRankName?: string;
  /** 发现组织编码 */
  discoverOrgCode?: string;
  /** 发现组织路径 */
  discoverOrgCodePath?: string;
  /** 发现人编码 */
  discoverUserCode?: string;
  /** 发现人姓名 */
  discoverUserName?: string;
  /** 发现时间 */
  discoverTime?: string;
  /** 隐患名称 */
  hiddenTroubleName?: string;
  /** 隐患描述 */
  hiddenTroubleDesc?: string;
  /** 危险源类型 */
  dangerType?: string;
  /** 隐患位置 */
  location?: string;
  /** 来源 */
  listsource?: string;
  /** 单位发现标志 */
  unitFindFlag?: string;
  /** 奖励金额 */
  rewardXl?: number;
  /** 显示顺序 */
  displayOrder?: number;
  /** 提交时间 */
  submitTime?: string;
  /** 关闭时间 */
  closeTime?: string;
  /** 业务来源 */
  bizSource?: string;
  /** 业务来源ID */
  bizSourceId?: string | number;
  /** IEQMS状态 */
  ieqmsStatus?: string;
  /** 用户职位ID */
  userJobId?: string | number;
  /** 删除标志 */
  deleteFlag?: string;
}

export interface HiddentroubleQuery extends PageQuery {
  /** 状态 */
  status?: string;
  /** 隐患类型名称 */
  hiddenTroubleTypeName?: string;
  /** 隐患等级 */
  hiddenTroubleRankName?: string;
  /** 发现组织编码 */
  discoverOrgCode?: string;
  /** 发现组织路径 */
  discoverOrgCodePath?: string;
  /** 发现人编码 */
  discoverUserCode?: string;
  /** 发现人姓名 */
  discoverUserName?: string;
  /** 隐患名称 */
  hiddenTroubleName?: string;
  /** 日期范围参数 */
  params?: any;
}
