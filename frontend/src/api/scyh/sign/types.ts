export interface SignVO {
  signStatus: any; // 签署状态
  partyBEmpCode: any; // 乙方工号
  partyAEmpCode: any; // 甲方工号
  partyBName: any; // 乙方姓名
  dept: any; // 部门
  partyA: any; // 甲方
  template: any; // 模板
  signTime: any; // 签署时间
}

export interface SignForm {
  signStatus?: any; // 签署状态
  partyBEmpCode?: any; // 乙方工号
  partyAEmpCode?: any; // 甲方工号
  partyBName?: any; // 乙方姓名
  dept?: any; // 部门
  partyA?: any; // 甲方
  template?: any; // 模板
  signTime?: any; // 签署时间
}

export interface SignQuery extends PageQuery {
  signStatus?: any; // 签署状态
  partyBEmpCode?: any; // 乙方工号
  partyAEmpCode?: any; // 甲方工号
  partyBName?: any; // 乙方姓名
  dept?: any; // 部门
  partyA?: any; // 甲方
}