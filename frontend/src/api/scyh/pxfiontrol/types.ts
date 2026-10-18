export interface PXFIONTROLVO {
  id: any; // 消防持证表ID
  useStatus: any; // 使用状态
  reviewRemainDays: any; // 应复审剩余天数
  orgName: any; // 所属机构
  fireControlCertificate: any; // 消防证书类别
  name: any; // 姓名
  gender: any; // 性别
  certificateType: any; // 证书类型
  field: any; // 证号
  field2: any; // 发证机构
  field3: any; // 初领日期
  validDate: any; // 有效期
  field4: any; // 应复审日期
  createBy: any; // 创建人
  createTime: any; // 创建时间
  certificateSyncTime: any; // 证书同步时间
}

export interface PXFIONTROLForm {
  id?: string | number; // 消防持证表ID
  useStatus?: any; // 使用状态
  reviewRemainDays?: any; // 应复审剩余天数
  orgName?: any; // 所属机构
  fireControlCertificate?: any; // 消防证书类别
  name?: any; // 姓名
  gender?: any; // 性别
  certificateType?: any; // 证书类型
  field?: any; // 证号
  field2?: any; // 发证机构
  field3?: any; // 初领日期
  validDate?: any; // 有效期
  field4?: any; // 应复审日期
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
  certificateSyncTime?: any; // 证书同步时间
}

export interface PXFIONTROLQuery extends PageQuery {
  useStatus?: any; // 使用状态
  reviewRemainDays?: any; // 应复审剩余天数
  orgName?: any; // 所属机构
  fireControlCertificate?: any; // 消防证书类别
  name?: any; // 姓名
}