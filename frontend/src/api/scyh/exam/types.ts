export interface ExamVO {
  id: any; // 考试发布ID
  field: any; // 参考人员保存状态
  field2: any; // 当前状态
  orgCode: any; // 组织编码
  orgName: any; // 所属机构
  examName: any; // 考试名称
  certificateName: any; // 证明名称
  paperName: any; // 试卷名称
  id2: any; // 安全学习主表ID
  examType: any; // 考试类型
  examTime: any; // 考试时间
  examTime2: any; // 考试时间起
  examTime3: any; // 考试时间止
  field3: any; // 已考人数
  field4: any; // 未考人数
  field5: any; // 平均分
  pass: any; // 合格率
  field6: any; // 不合格人数
  field7: any; // 参与率
  is: any; // 是否阅卷
  exam: any; // 考试次数
  field8: any; // 考前练习
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface ExamForm {
  id?: string | number; // 考试发布ID
  field?: any; // 参考人员保存状态
  field2?: any; // 当前状态
  orgCode?: any; // 组织编码
  orgName?: any; // 所属机构
  examName?: any; // 考试名称
  certificateName?: any; // 证明名称
  paperName?: any; // 试卷名称
  id2?: any; // 安全学习主表ID
  examType?: any; // 考试类型
  examTime?: any; // 考试时间
  examTime2?: any; // 考试时间起
  examTime3?: any; // 考试时间止
  field3?: any; // 已考人数
  field4?: any; // 未考人数
  field5?: any; // 平均分
  pass?: any; // 合格率
  field6?: any; // 不合格人数
  field7?: any; // 参与率
  is?: any; // 是否阅卷
  exam?: any; // 考试次数
  field8?: any; // 考前练习
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface ExamQuery extends PageQuery {
  field?: any; // 参考人员保存状态
  field2?: any; // 当前状态
  orgCode?: any; // 组织编码
  orgName?: any; // 所属机构
  examName?: any; // 考试名称
}