export interface WHMSDSVO {
  id: any; // hazardousId
  status: any; // 状态
  publishOrg: any; // 发布单位
  articleCode: any; // 目录序号
  hazardousName: any; // 危化品名称
  materialName: any; // 物料名称
  manufacturer: any; // 厂家
  field: any; // 剧毒属性
  field2: any; // 重点监管
  field3: any; // 易制毒
  field4: any; // 易制爆
  field5: any; // 其他
  casNo: any; // CAS号
  attachmentCount: any; // 附件数量
  createByName: any; // 创建人姓名
  msds: any; // MSDS发布日期
  createTime: any; // 创建时间
}

export interface WHMSDSForm {
  id?: string | number; // hazardousId
  status?: any; // 状态
  publishOrg?: any; // 发布单位
  articleCode?: any; // 目录序号
  hazardousName?: any; // 危化品名称
  materialName?: any; // 物料名称
  manufacturer?: any; // 厂家
  field?: any; // 剧毒属性
  field2?: any; // 重点监管
  field3?: any; // 易制毒
  field4?: any; // 易制爆
  field5?: any; // 其他
  casNo?: any; // CAS号
  attachmentCount?: any; // 附件数量
  createByName?: any; // 创建人姓名
  msds?: any; // MSDS发布日期
  createTime?: any; // 创建时间
}

export interface WHMSDSQuery extends PageQuery {
  status?: any; // 状态
  publishOrg?: any; // 发布单位
  articleCode?: any; // 目录序号
  hazardousName?: any; // 危化品名称
  materialName?: any; // 物料名称
}