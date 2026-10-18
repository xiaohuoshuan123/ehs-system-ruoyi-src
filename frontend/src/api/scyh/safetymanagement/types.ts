export interface SafetyManagementVO {
  useStatus: any; // 使用状态
  reviewRemainDays: any; // 应复审剩余天数
  id: any; // 安全管理人员证书表ID
  orgName: any; // 所属机构
  name: any; // 姓名
  postSeq: any; // 岗位序列
  post: any; // 岗位
  field: any; // 发证单位
  industry: any; // 行业类别
  validDate: any; // 有效期
  field2: any; // 应复审日期
  createBy: any; // 创建人
  createTime: any; // 创建时间
  certificateSyncTime: any; // 证书同步时间
}

export interface SafetyManagementForm {
  useStatus?: any; // 使用状态
  reviewRemainDays?: any; // 应复审剩余天数
  id?: string | number; // 安全管理人员证书表ID
  orgName?: any; // 所属机构
  name?: any; // 姓名
  postSeq?: any; // 岗位序列
  post?: any; // 岗位
  field?: any; // 发证单位
  industry?: any; // 行业类别
  validDate?: any; // 有效期
  field2?: any; // 应复审日期
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
  certificateSyncTime?: any; // 证书同步时间
}

export interface SafetyManagementQuery extends PageQuery {
  useStatus?: any; // 使用状态
  reviewRemainDays?: any; // 应复审剩余天数
  orgName?: any; // 所属机构
  name?: any; // 姓名
  postSeq?: any; // 岗位序列
}