export interface WHOUTSTOCKVO {
  id: any; // 入库ID
  documentStatus: any; // 单据状态
  approver: any; // 审批人
  documentCode: any; // 单据编号
  outTime: any; // 出库日期
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
  outstock: any; // 出库量
  field7: any; // 上次库存
  field8: any; // 当前库存
  outstock2: any; // 出库人
  field9: any; // 确认时间
  field10: any; // 用途
  createByName: any; // 创建人姓名
  createTime: any; // 创建时间
}

export interface WHOUTSTOCKForm {
  id?: string | number; // 入库ID
  documentStatus?: any; // 单据状态
  approver?: any; // 审批人
  documentCode?: any; // 单据编号
  outTime?: any; // 出库日期
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
  outstock?: any; // 出库量
  field7?: any; // 上次库存
  field8?: any; // 当前库存
  outstock2?: any; // 出库人
  field9?: any; // 确认时间
  field10?: any; // 用途
  createByName?: any; // 创建人姓名
  createTime?: any; // 创建时间
}

export interface WHOUTSTOCKQuery extends PageQuery {
  documentStatus?: any; // 单据状态
  approver?: any; // 审批人
  documentCode?: any; // 单据编号
  outTime?: any; // 出库日期
  dept?: any; // 部门
}