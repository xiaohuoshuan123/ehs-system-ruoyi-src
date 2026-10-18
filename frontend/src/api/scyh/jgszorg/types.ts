export interface JGSZORGVO {
  id: any; // ID
  orgName: any; // 机构名称
  safety: any; // 安全管理机构
  personCount: any; // 人员数量
  createTime: any; // 创建时间
}

export interface JGSZORGForm {
  id?: string | number; // ID
  orgName?: any; // 机构名称
  safety?: any; // 安全管理机构
  personCount?: any; // 人员数量
  createTime?: any; // 创建时间
}

export interface JGSZORGQuery extends PageQuery {
  orgName?: any; // 机构名称
  safety?: any; // 安全管理机构
  personCount?: any; // 人员数量
  createTime?: any; // 创建时间
}