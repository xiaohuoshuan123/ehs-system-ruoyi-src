export interface SCRIOLLISTVO {
  id: any; // 风险管控主项ID
  auditStatus: any; // 审核状态
  check: any; // 检查表状态
  orgName: any; // 所属机构
  field: any; // 作业步骤
  equipment: any; // 设备设施/作业设备
  field2: any; // 类别划分
  post: any; // 岗位
  danger: any; // 危险源编号
  field3: any; // 第一类危险源
  field4: any; // 第二类危险源
  field5: any; // 固有风险等级
  field6: any; // 控制风险等级
  field7: any; // 管控类型
  field8: any; // 可能发生的事故或职业病
  field9: any; // 匹配隐患
  status: any; // 状态
  duty: any; // 责任人
  createBy: any; // 创建人
  createTime: any; // 创建时间
  updateBy: any; // 更新人
  updateTime: any; // 更新时间
}

export interface SCRIOLLISTForm {
  id?: string | number; // 风险管控主项ID
  auditStatus?: any; // 审核状态
  check?: any; // 检查表状态
  orgName?: any; // 所属机构
  field?: any; // 作业步骤
  equipment?: any; // 设备设施/作业设备
  field2?: any; // 类别划分
  post?: any; // 岗位
  danger?: any; // 危险源编号
  field3?: any; // 第一类危险源
  field4?: any; // 第二类危险源
  field5?: any; // 固有风险等级
  field6?: any; // 控制风险等级
  field7?: any; // 管控类型
  field8?: any; // 可能发生的事故或职业病
  field9?: any; // 匹配隐患
  status?: any; // 状态
  duty?: any; // 责任人
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
  updateBy?: any; // 更新人
  updateTime?: any; // 更新时间
}

export interface SCRIOLLISTQuery extends PageQuery {
  auditStatus?: any; // 审核状态
  check?: any; // 检查表状态
  orgName?: any; // 所属机构
  field?: any; // 作业步骤
  equipment?: any; // 设备设施/作业设备
}