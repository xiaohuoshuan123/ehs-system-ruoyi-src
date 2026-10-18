export interface YJEMERPLANVO {
  id: any; // 应急预案id
  unitName: any; // 单位名称
  planName: any; // 预案名称
  planType: any; // 预案类型
  planLevel: any; // 预案等级
  field: any; // 编制部门
  publishDate: any; // 发布日期
  audit: any; // 审核人
  drill: any; // 演练周期
  attachmentCount: any; // 附件数量
  dataStatus: any; // 数据状态
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface YJEMERPLANForm {
  id?: string | number; // 应急预案id
  unitName?: any; // 单位名称
  planName?: any; // 预案名称
  planType?: any; // 预案类型
  planLevel?: any; // 预案等级
  field?: any; // 编制部门
  publishDate?: any; // 发布日期
  audit?: any; // 审核人
  drill?: any; // 演练周期
  attachmentCount?: any; // 附件数量
  dataStatus?: any; // 数据状态
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface YJEMERPLANQuery extends PageQuery {
  unitName?: any; // 单位名称
  planName?: any; // 预案名称
  planType?: any; // 预案类型
  planLevel?: any; // 预案等级
  field?: any; // 编制部门
}