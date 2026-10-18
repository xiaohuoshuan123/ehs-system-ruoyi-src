export interface QuestionsVO {
  id: any; // 试题ID
  field: any; // 题型
  field2: any; // 题干
  field3: any; // 解析
  field4: any; // 已考次数
  field5: any; // 错题次数
  createBy: any; // 创建人
  createTime: any; // 创建时间
  updateBy: any; // 更新人
  updateTime: any; // 更新时间
}

export interface QuestionsForm {
  id?: string | number; // 试题ID
  field?: any; // 题型
  field2?: any; // 题干
  field3?: any; // 解析
  field4?: any; // 已考次数
  field5?: any; // 错题次数
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
  updateBy?: any; // 更新人
  updateTime?: any; // 更新时间
}

export interface QuestionsQuery extends PageQuery {
  field?: any; // 题型
  field2?: any; // 题干
  field3?: any; // 解析
  field4?: any; // 已考次数
  field5?: any; // 错题次数
}