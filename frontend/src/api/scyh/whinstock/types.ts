export interface WHINSTOCKVO {
  id: any; // 入库ID
  documentCode: any; // 单据编号
  inTime: any; // 入库日期
  dept: any; // 部门
  storageName: any; // 存储库
  field: any; // 使用区域
  articleCode: any; // 目录序号
  hazardousName: any; // 危化品名称
  materialName: any; // 物料名称
  field2: any; // 剧毒品
  field3: any; // 易制爆品
  field4: any; // 易制毒品
  field5: any; // 重点监管品
  field6: any; // 其他
  spec: any; // 规格（数量/单位）
  instock: any; // 入库量
  field7: any; // 上次库存
  field8: any; // 当前库存
  field9: any; // 最大库存
  instock2: any; // 入库人
  field10: any; // 确认时间
  field11: any; // 用途
  createTime: any; // 创建时间
}

export interface WHINSTOCKForm {
  id?: string | number; // 入库ID
  documentCode?: any; // 单据编号
  inTime?: any; // 入库日期
  dept?: any; // 部门
  storageName?: any; // 存储库
  field?: any; // 使用区域
  articleCode?: any; // 目录序号
  hazardousName?: any; // 危化品名称
  materialName?: any; // 物料名称
  field2?: any; // 剧毒品
  field3?: any; // 易制爆品
  field4?: any; // 易制毒品
  field5?: any; // 重点监管品
  field6?: any; // 其他
  spec?: any; // 规格（数量/单位）
  instock?: any; // 入库量
  field7?: any; // 上次库存
  field8?: any; // 当前库存
  field9?: any; // 最大库存
  instock2?: any; // 入库人
  field10?: any; // 确认时间
  field11?: any; // 用途
  createTime?: any; // 创建时间
}

export interface WHINSTOCKQuery extends PageQuery {
  documentCode?: any; // 单据编号
  inTime?: any; // 入库日期
  dept?: any; // 部门
  storageName?: any; // 存储库
  field?: any; // 使用区域
}