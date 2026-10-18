export interface EducationTrainingRecordVO {
  id: any; // 记录id
  field: any; // 实施机构
  trainYear: any; // 培训年度
  courseName: any; // 课程名称
  startTime: any; // 开始时间
  endTime: any; // 结束时间
  trainHours: any; // 培训学时
  trainScore: any; // 培训成绩
  study: any; // 学习方式
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface EducationTrainingRecordForm {
  id?: string | number; // 记录id
  field?: any; // 实施机构
  trainYear?: any; // 培训年度
  courseName?: any; // 课程名称
  startTime?: any; // 开始时间
  endTime?: any; // 结束时间
  trainHours?: any; // 培训学时
  trainScore?: any; // 培训成绩
  study?: any; // 学习方式
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface EducationTrainingRecordQuery extends PageQuery {
  field?: any; // 实施机构
  trainYear?: any; // 培训年度
  courseName?: any; // 课程名称
  startTime?: any; // 开始时间
  endTime?: any; // 结束时间
}