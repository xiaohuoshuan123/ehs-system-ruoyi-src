export interface EnterprisePersonVO {
  id: any; // 企业负责人证书ID
  useStatus: any; // 使用状态
  reviewRemainDays: any; // 应复审剩余天数
  orgName: any; // 所属机构
  name: any; // 姓名
  post: any; // 岗位
  personType: any; // 人员类别
  level: any; // 层级
  field: any; // 发证单位
  industry: any; // 行业类别
  validDate: any; // 有效期
  field2: any; // 应复审日期
  createBy: any; // 创建人
  createTime: any; // 创建时间
  certificateSyncTime: any; // 证书同步时间
}

export interface EnterprisePersonForm {
  id?: string | number; // 企业负责人证书ID
  useStatus?: any; // 使用状态
  reviewRemainDays?: any; // 应复审剩余天数
  orgName?: any; // 所属机构
  name?: any; // 姓名
  post?: any; // 岗位
  personType?: any; // 人员类别
  level?: any; // 层级
  field?: any; // 发证单位
  industry?: any; // 行业类别
  validDate?: any; // 有效期
  field2?: any; // 应复审日期
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
  certificateSyncTime?: any; // 证书同步时间
}

export interface EnterprisePersonQuery extends PageQuery {
  useStatus?: any; // 使用状态
  reviewRemainDays?: any; // 应复审剩余天数
  orgName?: any; // 所属机构
  name?: any; // 姓名
  post?: any; // 岗位
}