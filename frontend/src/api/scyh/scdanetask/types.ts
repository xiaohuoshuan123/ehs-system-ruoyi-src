export interface SCDANETASKVO {
  id: any; // 危险源检查任务主项ID
  role: any; // 角色Ids
  field: any; // 任务状态
  checkRole: any; // 检查角色/责任人
  checkOrg: any; // 检查机构Code
  field2: any; // 被查机构
  checkLevel: any; // 检查层级
  field3: any; // 作业单元名称
  field4: any; // 固有风险等级
  check: any; // 检查频度
  field5: any; // 任务时间
  danger: any; // 危险源数量
  field6: any; // 实际检查数量
  field7: any; // 不受控数量
  checker: any; // 检查人员
  feedbackPerson: any; // 反馈人员
  feedbackTime: any; // 反馈时间
}

export interface SCDANETASKForm {
  id?: string | number; // 危险源检查任务主项ID
  role?: any; // 角色Ids
  field?: any; // 任务状态
  checkRole?: any; // 检查角色/责任人
  checkOrg?: any; // 检查机构Code
  field2?: any; // 被查机构
  checkLevel?: any; // 检查层级
  field3?: any; // 作业单元名称
  field4?: any; // 固有风险等级
  check?: any; // 检查频度
  field5?: any; // 任务时间
  danger?: any; // 危险源数量
  field6?: any; // 实际检查数量
  field7?: any; // 不受控数量
  checker?: any; // 检查人员
  feedbackPerson?: any; // 反馈人员
  feedbackTime?: any; // 反馈时间
}

export interface SCDANETASKQuery extends PageQuery {
  role?: any; // 角色Ids
  field?: any; // 任务状态
  checkRole?: any; // 检查角色/责任人
  checkOrg?: any; // 检查机构Code
  field2?: any; // 被查机构
}