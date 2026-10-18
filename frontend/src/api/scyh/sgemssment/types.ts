export interface SGEMSSMENTVO {
  id: any; // 应急处置评估ID
  eventId: any; // 事件报告ID
  status: any; // 状态
  eventTime: any; // 事件时间
  event: any; // 事件发生单位
  eventLevel: any; // 事件级别
  field: any; // 事发地点
  event2: any; // 事件简要经过
  emergencyHandle: any; // 应急处置情况
  is: any; // 是否涉及应急预案
  planIs: any; // 预案是否需要修订
}

export interface SGEMSSMENTForm {
  id?: string | number; // 应急处置评估ID
  eventId?: any; // 事件报告ID
  status?: any; // 状态
  eventTime?: any; // 事件时间
  event?: any; // 事件发生单位
  eventLevel?: any; // 事件级别
  field?: any; // 事发地点
  event2?: any; // 事件简要经过
  emergencyHandle?: any; // 应急处置情况
  is?: any; // 是否涉及应急预案
  planIs?: any; // 预案是否需要修订
}

export interface SGEMSSMENTQuery extends PageQuery {
  eventId?: any; // 事件报告ID
  status?: any; // 状态
  eventTime?: any; // 事件时间
  event?: any; // 事件发生单位
  eventLevel?: any; // 事件级别
}