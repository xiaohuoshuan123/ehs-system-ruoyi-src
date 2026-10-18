export interface YJEMTERIALVO {
  id: any; // 应急物资ID
  equipmentName: any; // 设备名称
  model: any; // 型号
  count: any; // 数量
  field: any; // 购置时间
  field2: any; // 下次检验日期
  field3: any; // 存放地点
  field4: any; // 用途
  equipment: any; // 设备状况
  field5: any; // 填报单位
  field6: any; // 联系人
  field7: any; // 联系电话
}

export interface YJEMTERIALForm {
  id?: string | number; // 应急物资ID
  equipmentName?: any; // 设备名称
  model?: any; // 型号
  count?: any; // 数量
  field?: any; // 购置时间
  field2?: any; // 下次检验日期
  field3?: any; // 存放地点
  field4?: any; // 用途
  equipment?: any; // 设备状况
  field5?: any; // 填报单位
  field6?: any; // 联系人
  field7?: any; // 联系电话
}

export interface YJEMTERIALQuery extends PageQuery {
  equipmentName?: any; // 设备名称
  model?: any; // 型号
  count?: any; // 数量
  field?: any; // 购置时间
  field2?: any; // 下次检验日期
}