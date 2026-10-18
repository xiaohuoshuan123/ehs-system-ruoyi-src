export interface EducationTrainingMstVO {
  id: any; // 再教育培训记录ID
  field: any; // 完成状态
  trainYear: any; // 培训年度
  orgName: any; // 所属机构
  employedStatus: any; // 在职状态
  participantPerson: any; // 参训人员
  dept: any; // 部门
  post: any; // 岗位
  train: any; // 培训次数
  hours: any; // 学时要求
  field2: any; // 实际学时
  field3: any; // 完成率
  createTime: any; // 创建时间
}

export interface EducationTrainingMstForm {
  id?: string | number; // 再教育培训记录ID
  field?: any; // 完成状态
  trainYear?: any; // 培训年度
  orgName?: any; // 所属机构
  employedStatus?: any; // 在职状态
  participantPerson?: any; // 参训人员
  dept?: any; // 部门
  post?: any; // 岗位
  train?: any; // 培训次数
  hours?: any; // 学时要求
  field2?: any; // 实际学时
  field3?: any; // 完成率
  createTime?: any; // 创建时间
}

export interface EducationTrainingMstQuery extends PageQuery {
  field?: any; // 完成状态
  trainYear?: any; // 培训年度
  orgName?: any; // 所属机构
  employedStatus?: any; // 在职状态
  participantPerson?: any; // 参训人员
}