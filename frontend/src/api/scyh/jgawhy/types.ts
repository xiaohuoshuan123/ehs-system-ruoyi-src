export interface JGAWHYVO {
  id: any; // 安委会会议纪要ID
  meetingTitle: any; // 会议标题
  publishTime: any; // 发布时间
  publishOrg: any; // 发布单位
  publisher: any; // 发布人
  type: any; // 类型
  meetingMaterial: any; // 会议材料
  meetingSummary: any; // 会议纪要
}

export interface JGAWHYForm {
  id?: string | number; // 安委会会议纪要ID
  meetingTitle?: any; // 会议标题
  publishTime?: any; // 发布时间
  publishOrg?: any; // 发布单位
  publisher?: any; // 发布人
  type?: any; // 类型
  meetingMaterial?: any; // 会议材料
  meetingSummary?: any; // 会议纪要
}

export interface JGAWHYQuery extends PageQuery {
  meetingTitle?: any; // 会议标题
  publishTime?: any; // 发布时间
  publishOrg?: any; // 发布单位
  publisher?: any; // 发布人
  type?: any; // 类型
}