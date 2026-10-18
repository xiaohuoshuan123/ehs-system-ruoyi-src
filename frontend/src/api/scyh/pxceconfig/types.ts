export interface PXCECONFIGVO {
  id: any; // 证书配置表明细ID
  id2: any; // 证书配置表ID
  isCompliant: any; // 是否合规
  configCode: any; // 配置编码
  orgCode: any; // 组织编码
  orgName: any; // 所属机构
  certificateType: any; // 证书类型
  field: any; // 作业类别
  field2: any; // 需持证数量
  field3: any; // 实际持证数量
  remark: any; // 备注
  field4: any; // 所属基地
  extField1: any; // 扩展字段1:系统上线前不使用
  extField2: any; // 扩展字段2:系统上线前不使用
  extField3: any; // 扩展字段3:系统上线前不使用
  createByEmpCode: any; // 创建人工号
  createByName: any; // 创建人姓名
  field5: any; // 修改人工号
  field6: any; // 修改人
  field7: any; // 修改时间
  delete: any; // 删除人工号
  delete2: any; // 删除人姓名
  deleteTime: any; // 删除时间
  delete3: any; // 删除标记
}

export interface PXCECONFIGForm {
  id?: string | number; // 证书配置表明细ID
  id2?: any; // 证书配置表ID
  isCompliant?: any; // 是否合规
  configCode?: any; // 配置编码
  orgCode?: any; // 组织编码
  orgName?: any; // 所属机构
  certificateType?: any; // 证书类型
  field?: any; // 作业类别
  field2?: any; // 需持证数量
  field3?: any; // 实际持证数量
  remark?: any; // 备注
  field4?: any; // 所属基地
  1?: any; // 扩展字段1:系统上线前不使用
  2?: any; // 扩展字段2:系统上线前不使用
  3?: any; // 扩展字段3:系统上线前不使用
  createByEmpCode?: any; // 创建人工号
  createByName?: any; // 创建人姓名
  field5?: any; // 修改人工号
  field6?: any; // 修改人
  field7?: any; // 修改时间
  delete?: any; // 删除人工号
  delete2?: any; // 删除人姓名
  deleteTime?: any; // 删除时间
  delete3?: any; // 删除标记
}

export interface PXCECONFIGQuery extends PageQuery {
  id2?: any; // 证书配置表ID
  isCompliant?: any; // 是否合规
  configCode?: any; // 配置编码
  orgCode?: any; // 组织编码
  orgName?: any; // 所属机构
}