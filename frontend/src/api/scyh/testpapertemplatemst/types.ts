export interface TestPaperTemplateMstVO {
  id: any; // 试卷模板ID
  templateName: any; // 模板名称
  field: any; // 总题数
  field2: any; // 总分
  pass: any; // 合格分数线
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface TestPaperTemplateMstForm {
  id?: string | number; // 试卷模板ID
  templateName?: any; // 模板名称
  field?: any; // 总题数
  field2?: any; // 总分
  pass?: any; // 合格分数线
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface TestPaperTemplateMstQuery extends PageQuery {
  templateName?: any; // 模板名称
  field?: any; // 总题数
  field2?: any; // 总分
  pass?: any; // 合格分数线
  createBy?: any; // 创建人
}