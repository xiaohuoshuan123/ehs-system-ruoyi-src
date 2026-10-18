export interface WZCLBREAVO {
  id: any; // 违章处理主键
  handleStatus: any; // 处理状态
  breachTime: any; // 违章时间
  breachLevel: any; // 违章级别
  breachDept: any; // 违章部门
  checkOrg: any; // 检查机构
  handleOrg: any; // 处理机构
  breachDesc: any; // 违章描述
  personNature: any; // 违章人员性质
  breachPerson: any; // 违章人员
  checker: any; // 检查人员
  handler: any; // 处理人员
  handleDate: any; // 处理日期
  createTime: any; // 创建时间
}

export interface WZCLBREAForm {
  id?: string | number; // 违章处理主键
  handleStatus?: any; // 处理状态
  breachTime?: any; // 违章时间
  breachLevel?: any; // 违章级别
  breachDept?: any; // 违章部门
  checkOrg?: any; // 检查机构
  handleOrg?: any; // 处理机构
  breachDesc?: any; // 违章描述
  personNature?: any; // 违章人员性质
  breachPerson?: any; // 违章人员
  checker?: any; // 检查人员
  handler?: any; // 处理人员
  handleDate?: any; // 处理日期
  createTime?: any; // 创建时间
}

export interface WZCLBREAQuery extends PageQuery {
  handleStatus?: any; // 处理状态
  breachTime?: any; // 违章时间
  breachLevel?: any; // 违章级别
  breachDept?: any; // 违章部门
  checkOrg?: any; // 检查机构
}