export interface MenuVO {
  id: any; // 固定动火点ID
  auditStatusCode: any; // 审核状态编码
  auditStatus: any; // 审核状态
  useStatus: any; // 使用状态
  hotWork: any; // 动火点编号
  dutyUnit: any; // 责任单位
  field: any; // 作业单元
  field2: any; // 具体位置
  duty: any; // 责任人
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface MenuForm {
  id?: string | number; // 固定动火点ID
  auditStatusCode?: any; // 审核状态编码
  auditStatus?: any; // 审核状态
  useStatus?: any; // 使用状态
  hotWork?: any; // 动火点编号
  dutyUnit?: any; // 责任单位
  field?: any; // 作业单元
  field2?: any; // 具体位置
  duty?: any; // 责任人
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface MenuQuery extends PageQuery {
  auditStatusCode?: any; // 审核状态编码
  auditStatus?: any; // 审核状态
  useStatus?: any; // 使用状态
  hotWork?: any; // 动火点编号
  dutyUnit?: any; // 责任单位
}