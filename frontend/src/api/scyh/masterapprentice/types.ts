export interface MasterApprenticeVO {
  masterName: any; // 师傅姓名
  masterOrgName: any; // 师傅所属机构
  apprenticeName: any; // 徒弟姓名
  apprenticeOrgName: any; // 徒弟所属机构
  signDateDate: any; // 签订日期
  startDate: any; // 开始日期
  endDate: any; // 结束日期
  status: any; // 状态
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface MasterApprenticeForm {
  masterName?: any; // 师傅姓名
  masterOrgName?: any; // 师傅所属机构
  apprenticeName?: any; // 徒弟姓名
  apprenticeOrgName?: any; // 徒弟所属机构
  signDateDate?: any; // 签订日期
  startDate?: any; // 开始日期
  endDate?: any; // 结束日期
  status?: any; // 状态
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface MasterApprenticeQuery extends PageQuery {
  masterName?: any; // 师傅姓名
  masterOrgName?: any; // 师傅所属机构
  apprenticeName?: any; // 徒弟姓名
  apprenticeOrgName?: any; // 徒弟所属机构
  signDateDate?: any; // 签订日期
  startDate?: any; // 开始日期
}