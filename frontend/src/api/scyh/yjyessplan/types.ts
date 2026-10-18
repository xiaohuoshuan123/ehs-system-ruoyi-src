export interface YJYESSPLANVO {
  id: any; // 应急演练评估ID
  field: any; // 填报状态
  approveStatus: any; // 审批状态
  field2: any; // 当前处理人
  unitName: any; // 单位名称
  drillType: any; // 演练类型
  planName: any; // 预案名称
  planLevel: any; // 预案等级
  planTime: any; // 计划时间
  drillDate: any; // 演练日期
  field3: any; // 参演人数
  drill: any; // 演练投入(万元)
  planTime2: any; // 计划时间差异分析
  attachmentCount: any; // 附件数量
  field4: any; // 修改人
  field5: any; // 修改时间
}

export interface YJYESSPLANForm {
  id?: string | number; // 应急演练评估ID
  field?: any; // 填报状态
  approveStatus?: any; // 审批状态
  field2?: any; // 当前处理人
  unitName?: any; // 单位名称
  drillType?: any; // 演练类型
  planName?: any; // 预案名称
  planLevel?: any; // 预案等级
  planTime?: any; // 计划时间
  drillDate?: any; // 演练日期
  field3?: any; // 参演人数
  drill?: any; // 演练投入(万元)
  planTime2?: any; // 计划时间差异分析
  attachmentCount?: any; // 附件数量
  field4?: any; // 修改人
  field5?: any; // 修改时间
}

export interface YJYESSPLANQuery extends PageQuery {
  field?: any; // 填报状态
  approveStatus?: any; // 审批状态
  field2?: any; // 当前处理人
  unitName?: any; // 单位名称
  drillType?: any; // 演练类型
}