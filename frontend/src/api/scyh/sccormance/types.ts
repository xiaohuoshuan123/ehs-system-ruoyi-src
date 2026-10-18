export interface SCCORMANCEVO {
  id: any; // 评审ID
  id2: any; // 风险管控主项ID
  field: any; // 完成状态
  orgName: any; // 机构名称
  field2: any; // 作业单元
  field3: any; // 作业步骤/设备设施
  danger: any; // 危险源ID
  danger2: any; // 危险源编号
  field4: any; // 第一类危险源
  field5: any; // 第二类危险源
  field6: any; // 前固有风险等级
  field7: any; // 前控制风险等级
  field8: any; // 现固有风险等级
  field9: any; // 现控制风险等级
  field10: any; // 控制绩效评价
  field11: any; // 评审日期
}

export interface SCCORMANCEForm {
  id?: string | number; // 评审ID
  id2?: any; // 风险管控主项ID
  field?: any; // 完成状态
  orgName?: any; // 机构名称
  field2?: any; // 作业单元
  field3?: any; // 作业步骤/设备设施
  danger?: any; // 危险源ID
  danger2?: any; // 危险源编号
  field4?: any; // 第一类危险源
  field5?: any; // 第二类危险源
  field6?: any; // 前固有风险等级
  field7?: any; // 前控制风险等级
  field8?: any; // 现固有风险等级
  field9?: any; // 现控制风险等级
  field10?: any; // 控制绩效评价
  field11?: any; // 评审日期
}

export interface SCCORMANCEQuery extends PageQuery {
  id2?: any; // 风险管控主项ID
  field?: any; // 完成状态
  orgName?: any; // 机构名称
  field2?: any; // 作业单元
  field3?: any; // 作业步骤/设备设施
}