export interface JGZRDUTYVO {
  id: any; // 安全生产责任制表ID
  status: any; // 状态
  orgName: any; // 机构名称
  postName: any; // 岗位名称
  post: any; // 岗位职责
}

export interface JGZRDUTYForm {
  id?: string | number; // 安全生产责任制表ID
  status?: any; // 状态
  orgName?: any; // 机构名称
  postName?: any; // 岗位名称
  post?: any; // 岗位职责
}

export interface JGZRDUTYQuery extends PageQuery {
  status?: any; // 状态
  orgName?: any; // 机构名称
  postName?: any; // 岗位名称
  post?: any; // 岗位职责
}