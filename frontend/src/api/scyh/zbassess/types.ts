export interface ZBASSESSVO {
  id: any; // 合规性评价ID
  field: any; // 专业管理部门名称
  year: any; // 年度
  field2: any; // 评价报告
}

export interface ZBASSESSForm {
  id?: string | number; // 合规性评价ID
  field?: any; // 专业管理部门名称
  year?: any; // 年度
  field2?: any; // 评价报告
}

export interface ZBASSESSQuery extends PageQuery {
  field?: any; // 专业管理部门名称
  year?: any; // 年度
  field2?: any; // 评价报告
}