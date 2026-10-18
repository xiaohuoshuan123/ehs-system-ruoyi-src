export interface MBJHPJVO {
  id: any; // 年度计划ID
  status: any; // 状态
  planName: any; // 计划名称
  planYear: any; // 计划年度
  dutyUnit: any; // 责任单位
  feedbackCount: any; // 反馈数量
  field: any; // 未反馈数量
  createBy: any; // 创建人
  createTime: any; // 创建时间
  field2: any; // 置顶状态
}

export interface MBJHPJForm {
  id?: string | number; // 年度计划ID
  status?: any; // 状态
  planName?: any; // 计划名称
  planYear?: any; // 计划年度
  dutyUnit?: any; // 责任单位
  feedbackCount?: any; // 反馈数量
  field?: any; // 未反馈数量
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
  field2?: any; // 置顶状态
}

export interface MBJHPJQuery extends PageQuery {
  status?: any; // 状态
  planName?: any; // 计划名称
  planYear?: any; // 计划年度
  dutyUnit?: any; // 责任单位
  feedbackCount?: any; // 反馈数量
}