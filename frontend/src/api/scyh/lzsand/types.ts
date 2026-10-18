export interface LZSANDVO {
  id: any; // 模型ID
  status: any; // 状态
  field: any; // 所属模块
  indicatorCode: any; // 指标编号
  indicatorName: any; // 指标名称
  deductScore: any; // 扣分规则
  dataSource: any; // 数据来源
}

export interface LZSANDForm {
  id?: string | number; // 模型ID
  status?: any; // 状态
  field?: any; // 所属模块
  indicatorCode?: any; // 指标编号
  indicatorName?: any; // 指标名称
  deductScore?: any; // 扣分规则
  dataSource?: any; // 数据来源
}

export interface LZSANDQuery extends PageQuery {
  status?: any; // 状态
  field?: any; // 所属模块
  indicatorCode?: any; // 指标编号
  indicatorName?: any; // 指标名称
  deductScore?: any; // 扣分规则
}