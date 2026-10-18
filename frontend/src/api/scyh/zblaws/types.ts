export interface ZBLAWSVO {
  id: any; // 法律主键
  publishStatus: any; // 发布状态
  field: any; // 效力状态
  fileNo: any; // 文号
  fileName: any; // 文件名称
  alias: any; // 别名/简称
  fileCategory: any; // 文件分类
  publishDate: any; // 发布日期
  effectiveDate: any; // 实施日期
  publishDept: any; // 发布部门/制定单位
  source: any; // 来源
  attachmentCount: any; // 附件数量
  uploader: any; // 上传人
  uploadTime: any; // 上传时间
}

export interface ZBLAWSForm {
  id?: string | number; // 法律主键
  publishStatus?: any; // 发布状态
  field?: any; // 效力状态
  fileNo?: any; // 文号
  fileName?: any; // 文件名称
  alias?: any; // 别名/简称
  fileCategory?: any; // 文件分类
  publishDate?: any; // 发布日期
  effectiveDate?: any; // 实施日期
  publishDept?: any; // 发布部门/制定单位
  source?: any; // 来源
  attachmentCount?: any; // 附件数量
  uploader?: any; // 上传人
  uploadTime?: any; // 上传时间
}

export interface ZBLAWSQuery extends PageQuery {
  publishStatus?: any; // 发布状态
  field?: any; // 效力状态
  fileNo?: any; // 文号
  fileName?: any; // 文件名称
  alias?: any; // 别名/简称
}