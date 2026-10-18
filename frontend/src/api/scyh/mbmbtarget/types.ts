export interface MBMBTARGETVO {
  id: any; // 年度目标ID
  targetName: any; // 目标名称
  year: any; // 年度
  unitName: any; // 单位名称
  indicatorCount: any; // 指标数量
  targetDuty: any; // 目标责任书
  yearTarget: any; // 年度目标完成情况
  createByEmpCode: any; // 创建人工号
  createTime: any; // 创建时间
}

export interface MBMBTARGETForm {
  id?: string | number; // 年度目标ID
  targetName?: any; // 目标名称
  year?: any; // 年度
  unitName?: any; // 单位名称
  indicatorCount?: any; // 指标数量
  targetDuty?: any; // 目标责任书
  yearTarget?: any; // 年度目标完成情况
  createByEmpCode?: any; // 创建人工号
  createTime?: any; // 创建时间
}

export interface MBMBTARGETQuery extends PageQuery {
  targetName?: any; // 目标名称
  year?: any; // 年度
  unitName?: any; // 单位名称
  indicatorCount?: any; // 指标数量
  targetDuty?: any; // 目标责任书
}