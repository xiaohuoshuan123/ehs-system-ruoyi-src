export interface SCDAECONFIVO {
  id: any; // 危险源检查配置ID
  status: any; // 状态
  checkLevel: any; // 检查层级
  riskLevel: any; // 风险等级
  check: any; // 检查频次
  roleName: any; // 角色名称
  duty: any; // 责任人
  createByName: any; // 创建人姓名
  createTime: any; // 创建时间
  field: any; // 修改人姓名
  field2: any; // 修改时间
}

export interface SCDAECONFIForm {
  id?: string | number; // 危险源检查配置ID
  status?: any; // 状态
  checkLevel?: any; // 检查层级
  riskLevel?: any; // 风险等级
  check?: any; // 检查频次
  roleName?: any; // 角色名称
  duty?: any; // 责任人
  createByName?: any; // 创建人姓名
  createTime?: any; // 创建时间
  field?: any; // 修改人姓名
  field2?: any; // 修改时间
}

export interface SCDAECONFIQuery extends PageQuery {
  status?: any; // 状态
  checkLevel?: any; // 检查层级
  riskLevel?: any; // 风险等级
  check?: any; // 检查频次
  roleName?: any; // 角色名称
}