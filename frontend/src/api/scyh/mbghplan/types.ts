export interface MBGHPLANVO {
  id: any; // id
  year: any; // 年度
  name: any; // 名称
  field: any; // 大小
  uploader: any; // 上传人
  uploadTime: any; // 上传时间
}

export interface MBGHPLANForm {
  id?: string | number; // id
  year?: any; // 年度
  name?: any; // 名称
  field?: any; // 大小
  uploader?: any; // 上传人
  uploadTime?: any; // 上传时间
}

export interface MBGHPLANQuery extends PageQuery {
  year?: any; // 年度
  name?: any; // 名称
  field?: any; // 大小
  uploader?: any; // 上传人
  uploadTime?: any; // 上传时间
}