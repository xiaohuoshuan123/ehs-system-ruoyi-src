export interface SafetyStudyMstVO {
  id: any; // 安全学习主表ID
  status: any; // 状态
  publishOrg: any; // 发布机构
  studyRangeCode: any; // 学习范围编码
  studyRangeCode2: any; // 学习范围编码全编码
  studyRange: any; // 学习范围
  study: any; // 学习期限
  material: any; // 材料类别
  is: any; // 是否必学
  materialName: any; // 材料名称
  field: any; // 关联考试
  field2: any; // 公共材料
  field3: any; // 已学人数
  field4: any; // 未学人数
  attachmentCount: any; // 附件数量
  createByName: any; // 创建人姓名
  createTime: any; // 创建时间
  updateByName: any; // 更新人姓名
  updateTime: any; // 更新时间
}

export interface SafetyStudyMstForm {
  id?: string | number; // 安全学习主表ID
  status?: any; // 状态
  publishOrg?: any; // 发布机构
  studyRangeCode?: any; // 学习范围编码
  studyRangeCode2?: any; // 学习范围编码全编码
  studyRange?: any; // 学习范围
  study?: any; // 学习期限
  material?: any; // 材料类别
  is?: any; // 是否必学
  materialName?: any; // 材料名称
  field?: any; // 关联考试
  field2?: any; // 公共材料
  field3?: any; // 已学人数
  field4?: any; // 未学人数
  attachmentCount?: any; // 附件数量
  createByName?: any; // 创建人姓名
  createTime?: any; // 创建时间
  updateByName?: any; // 更新人姓名
  updateTime?: any; // 更新时间
}

export interface SafetyStudyMstQuery extends PageQuery {
  status?: any; // 状态
  publishOrg?: any; // 发布机构
  studyRangeCode?: any; // 学习范围编码
  studyRangeCode2?: any; // 学习范围编码全编码
  studyRange?: any; // 学习范围
}