export interface WHSTOCKVO {
  id: any; // 库存ID
  id2: any; // 存储库ID
  orgName: any; // 所属机构
  siteCode: any; // 场所编码
  storageName: any; // 存储库
  location: any; // 库位
  articleCode: any; // 目录序号
  hazardousName: any; // 危化品名称
  materialName: any; // 物料名称
  casNo: any; // CAS号
  spec: any; // 规格
  specUnit: any; // 规格单位code
  specUnit2: any; // 规格单位name
  field: any; // 当前库存量
  field2: any; // 最大存放量
  field3: any; // 标准库存量
  field4: any; // 所属基地
  extField1: any; // 扩展字段1
  extField2: any; // 扩展字段2
  extField3: any; // 扩展字段3
  createByEmpCode: any; // 创建人工号
  createByName: any; // 创建人姓名
  createTime: any; // 创建时间
  field5: any; // 修改人工号
  field6: any; // 修改人姓名
  field7: any; // 修改时间
  delete: any; // 删除人工号
  delete2: any; // 删除人姓名
  deleteTime: any; // 删除时间
  delete3: any; // 删除标记
  kgl: any; // 标准库存单位：重量：KG,体积：L
}

export interface WHSTOCKForm {
  id?: string | number; // 库存ID
  id2?: any; // 存储库ID
  orgName?: any; // 所属机构
  siteCode?: any; // 场所编码
  storageName?: any; // 存储库
  location?: any; // 库位
  articleCode?: any; // 目录序号
  hazardousName?: any; // 危化品名称
  materialName?: any; // 物料名称
  casNo?: any; // CAS号
  spec?: any; // 规格
  specUnit?: any; // 规格单位code
  specUnit2?: any; // 规格单位name
  field?: any; // 当前库存量
  field2?: any; // 最大存放量
  field3?: any; // 标准库存量
  field4?: any; // 所属基地
  1?: any; // 扩展字段1
  2?: any; // 扩展字段2
  3?: any; // 扩展字段3
  createByEmpCode?: any; // 创建人工号
  createByName?: any; // 创建人姓名
  createTime?: any; // 创建时间
  field5?: any; // 修改人工号
  field6?: any; // 修改人姓名
  field7?: any; // 修改时间
  delete?: any; // 删除人工号
  delete2?: any; // 删除人姓名
  deleteTime?: any; // 删除时间
  delete3?: any; // 删除标记
  kgl?: any; // 标准库存单位：重量：KG,体积：L
}

export interface WHSTOCKQuery extends PageQuery {
  id2?: any; // 存储库ID
  orgName?: any; // 所属机构
  siteCode?: any; // 场所编码
  storageName?: any; // 存储库
  location?: any; // 库位
}