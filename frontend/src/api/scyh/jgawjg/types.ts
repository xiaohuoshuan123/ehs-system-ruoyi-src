export interface JGAWJGVO {
  id: any; // 安委会架构ID
  orgName: any; // 机构名称
  safety: any; // 安全管理机构
  personCount: any; // 人员数量
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface JGAWJGForm {
  id?: string | number; // 安委会架构ID
  orgName?: any; // 机构名称
  safety?: any; // 安全管理机构
  personCount?: any; // 人员数量
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface JGAWJGQuery extends PageQuery {
  orgName?: any; // 机构名称
  safety?: any; // 安全管理机构
  personCount?: any; // 人员数量
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}