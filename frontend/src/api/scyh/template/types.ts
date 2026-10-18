export interface TemplateVO {
  status: any; // 状态
  orgName: any; // 所属机构
  templateName: any; // 模板名称
  field: any; // 职务
  field2: any; // 附则
  createBy: any; // 创建人
  createTime: any; // 创建时间
  updateBy: any; // 更新人
  updateTime: any; // 更新时间
}

export interface TemplateForm {
  status?: any; // 状态
  orgName?: any; // 所属机构
  templateName?: any; // 模板名称
  field?: any; // 职务
  field2?: any; // 附则
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
  updateBy?: any; // 更新人
  updateTime?: any; // 更新时间
}

export interface TemplateQuery extends PageQuery {
  status?: any; // 状态
  orgName?: any; // 所属机构
  templateName?: any; // 模板名称
  field?: any; // 职务
  field2?: any; // 附则
  createBy?: any; // 创建人
}