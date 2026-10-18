export interface DispatchVO {
  template: any; // 模板
  year: any; // 年度
  partyA: any; // 甲方
  partyB: any; // 乙方签字
  partyA2: any; // 甲方签字
  partyB2: any; // 乙方人数
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface DispatchForm {
  template?: any; // 模板
  year?: any; // 年度
  partyA?: any; // 甲方
  partyB?: any; // 乙方签字
  partyA2?: any; // 甲方签字
  partyB2?: any; // 乙方人数
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface DispatchQuery extends PageQuery {
  template?: any; // 模板
  year?: any; // 年度
  partyA?: any; // 甲方
  partyB?: any; // 乙方签字
  partyA2?: any; // 甲方签字
  partyB2?: any; // 乙方人数
}