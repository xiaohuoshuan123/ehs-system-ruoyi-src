export interface WZJLBREAVO {
  id: any; // 违章记录主键
  status: any; // 状态
  breachDesc: any; // 违章描述
  breachTime: any; // 违章时间
  breachLevel: any; // 违章级别
  breachDept: any; // 违章部门
  breachLocation: any; // 违章地点
  breachOwner: any; // 违章归属
  checkOrg: any; // 检查机构
  handleOrg: any; // 处理机构
  personNature: any; // 违章人员性质
  breachPerson: any; // 违章人员
  checker: any; // 检查人员
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface WZJLBREAForm {
  id?: string | number; // 违章记录主键
  status?: any; // 状态
  breachDesc?: any; // 违章描述
  breachTime?: any; // 违章时间
  breachLevel?: any; // 违章级别
  breachDept?: any; // 违章部门
  breachLocation?: any; // 违章地点
  breachOwner?: any; // 违章归属
  checkOrg?: any; // 检查机构
  handleOrg?: any; // 处理机构
  personNature?: any; // 违章人员性质
  breachPerson?: any; // 违章人员
  checker?: any; // 检查人员
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface WZJLBREAQuery extends PageQuery {
  status?: any; // 状态
  breachDesc?: any; // 违章描述
  breachTime?: any; // 违章时间
  breachLevel?: any; // 违章级别
  breachDept?: any; // 违章部门
}