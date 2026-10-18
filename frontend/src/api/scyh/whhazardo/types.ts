export interface WHHAZARDOVO {
  id: any; // 危化品目录ID
  articleCode: any; // 目录序号
  articleName: any; // 品名
  alias: any; // 别名
  casNo: any; // CAS号
  danger: any; // 危险性
  chemicalCategory: any; // 化学品分类
  field: any; // 剧毒属性
  field2: any; // 重点监管品名
  field3: any; // 易制爆品
  field4: any; // 易制毒品
  field5: any; // 其他
}

export interface WHHAZARDOForm {
  id?: string | number; // 危化品目录ID
  articleCode?: any; // 目录序号
  articleName?: any; // 品名
  alias?: any; // 别名
  casNo?: any; // CAS号
  danger?: any; // 危险性
  chemicalCategory?: any; // 化学品分类
  field?: any; // 剧毒属性
  field2?: any; // 重点监管品名
  field3?: any; // 易制爆品
  field4?: any; // 易制毒品
  field5?: any; // 其他
}

export interface WHHAZARDOQuery extends PageQuery {
  articleCode?: any; // 目录序号
  articleName?: any; // 品名
  alias?: any; // 别名
  casNo?: any; // CAS号
  danger?: any; // 危险性
}