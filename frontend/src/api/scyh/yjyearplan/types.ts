export interface YJYEARPLANVO {
  id: any; // 应急演练评估ID
  field: any; // 填报状态
  unitName: any; // 单位名称
  drillType: any; // 演练类型
  planName: any; // 预案名称
  planLevel: any; // 预案等级
  planTime: any; // 计划时间
  attachmentCount: any; // 附件数量
  field2: any; // 修改人
  field3: any; // 修改时间
}

export interface YJYEARPLANForm {
  id?: string | number; // 应急演练评估ID
  field?: any; // 填报状态
  unitName?: any; // 单位名称
  drillType?: any; // 演练类型
  planName?: any; // 预案名称
  planLevel?: any; // 预案等级
  planTime?: any; // 计划时间
  attachmentCount?: any; // 附件数量
  field2?: any; // 修改人
  field3?: any; // 修改时间
}

export interface YJYEARPLANQuery extends PageQuery {
  field?: any; // 填报状态
  unitName?: any; // 单位名称
  drillType?: any; // 演练类型
  planName?: any; // 预案名称
  planLevel?: any; // 预案等级
}