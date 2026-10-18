export interface ZBCOMPANYVO {
  id: any; // 转化公司管理规定ID
  field: any; // 专业管理部门
  lawRegulation: any; // 法律法规及其他要求名称
  field2: any; // 对应公司级管理文件
  field3: any; // 填写日期
}

export interface ZBCOMPANYForm {
  id?: string | number; // 转化公司管理规定ID
  field?: any; // 专业管理部门
  lawRegulation?: any; // 法律法规及其他要求名称
  field2?: any; // 对应公司级管理文件
  field3?: any; // 填写日期
}

export interface ZBCOMPANYQuery extends PageQuery {
  field?: any; // 专业管理部门
  lawRegulation?: any; // 法律法规及其他要求名称
  field2?: any; // 对应公司级管理文件
  field3?: any; // 填写日期
}