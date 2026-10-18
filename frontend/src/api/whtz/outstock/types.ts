export interface OutstockVO {
  /**
   * outstock
   */
  id: string | number;
  /**
   * outstock
   */
  articleCode: string;
  /**
   * outstock
   */
  articleName: string;
  /**
   * outstock
   */
  storageName: string;
  /**
   * outstock
   */
  outTime: string;
  /**
   * outstock
   */
  status: string;
}

export interface OutstockForm extends BaseEntity {
  /**
   * outstock
   */
  id?: string | number;
  /**
   * outstock
   */
  state?: string;
  /**
   * outstock
   */
  outstockStatus?: string;
  /**
   * outstock
   */
  articleCode?: string;
  /**
   * outstock
   */
  articleName?: string;
  /**
   * outstock
   */
  storageName?: string;
  /**
   * outstock
   */
  outquantity?: number;
  /**
   * outstock
   */
  outTime?: string;
  /**
   * outstock
   */
  operator?: string;
  /**
   * outstock
   */
  status?: string;
}

export interface OutstockQuery extends PageQuery {
  /**
   * outstock
   */
  articleCode?: string;
  /**
   * outstock
   */
  articleName?: string;
  /**
   * outstock
   */
  storageName?: string;
  /**
   * outstock
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
