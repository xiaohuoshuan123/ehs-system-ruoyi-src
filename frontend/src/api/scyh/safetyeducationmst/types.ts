export interface SafetyEducationMstVO {
  id: any; // 三级安全教育主表ID
  field: any; // 教育状态
  orgName: any; // 所属机构
  employedStatus: any; // 在职状态
  trainName: any; // 培训名称
  participantPerson: any; // 参训人员
  post: any; // 岗位
  train: any; // 培训类别名称
  field2: any; // 一级
  field3: any; // 二级
  field4: any; // 三级
  field5: any; // 考核时间
  field6: any; // 考核分数
  isPass: any; // 是否合格
}

export interface SafetyEducationMstForm {
  id?: string | number; // 三级安全教育主表ID
  field?: any; // 教育状态
  orgName?: any; // 所属机构
  employedStatus?: any; // 在职状态
  trainName?: any; // 培训名称
  participantPerson?: any; // 参训人员
  post?: any; // 岗位
  train?: any; // 培训类别名称
  field2?: any; // 一级
  field3?: any; // 二级
  field4?: any; // 三级
  field5?: any; // 考核时间
  field6?: any; // 考核分数
  isPass?: any; // 是否合格
}

export interface SafetyEducationMstQuery extends PageQuery {
  field?: any; // 教育状态
  orgName?: any; // 所属机构
  employedStatus?: any; // 在职状态
  trainName?: any; // 培训名称
  participantPerson?: any; // 参训人员
}