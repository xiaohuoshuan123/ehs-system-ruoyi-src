export interface WZTKBRSCVO {
  id: any; // 违章记分条款主键
  status: any; // 状态
  publishOrg: any; // 发布单位
  breachLevel: any; // 违章级别
  clauseNo: any; // 条款编号
  clauseContent: any; // 条款内容
  score: any; // 分值
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface WZTKBRSCForm {
  id?: string | number; // 违章记分条款主键
  status?: any; // 状态
  publishOrg?: any; // 发布单位
  breachLevel?: any; // 违章级别
  clauseNo?: any; // 条款编号
  clauseContent?: any; // 条款内容
  score?: any; // 分值
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface WZTKBRSCQuery extends PageQuery {
  status?: any; // 状态
  publishOrg?: any; // 发布单位
  breachLevel?: any; // 违章级别
  clauseNo?: any; // 条款编号
  clauseContent?: any; // 条款内容
}