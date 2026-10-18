export interface WHSTORAGEVO {
  id: any; // 存储库ID
  org: any; // 机构
  siteCode: any; // 场所编码
  orgCode: any; // 机构编码
  siteName: any; // 场所名称
  location: any; // 位置
  field: any; // 联系人
  field2: any; // 联系人电话
  createByEmpCode: any; // 创建人工号
  createByName: any; // 创建人姓名
  createTime: any; // 创建时间
  dangerSite: any; // 危险场所说明
}

export interface WHSTORAGEForm {
  id?: string | number; // 存储库ID
  org?: any; // 机构
  siteCode?: any; // 场所编码
  orgCode?: any; // 机构编码
  siteName?: any; // 场所名称
  location?: any; // 位置
  field?: any; // 联系人
  field2?: any; // 联系人电话
  createByEmpCode?: any; // 创建人工号
  createByName?: any; // 创建人姓名
  createTime?: any; // 创建时间
  dangerSite?: any; // 危险场所说明
}

export interface WHSTORAGEQuery extends PageQuery {
  org?: any; // 机构
  siteCode?: any; // 场所编码
  orgCode?: any; // 机构编码
  siteName?: any; // 场所名称
  location?: any; // 位置
}