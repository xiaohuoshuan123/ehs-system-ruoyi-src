export interface InstockVO {
  /**
   * instock
   */
  id: string | number;
  /**
   * instock
   */
  articleCode: string;
  /**
   * instock
   */
  articleName: string;
  /**
   * instock
   */
  storageName: string;
  /**
   * instock
   */
  inTime: string;
  /**
   * instock
   */
  status: string;
}

export interface InstockForm extends BaseEntity {
  /**
   * instock
   */
  id?: string | number;
  /**
   * instock
   */
  state?: string;
  /**
   * instock
   */
  articleCode?: string;
  /**
   * instock
   */
  articleName?: string;
  /**
   * instock
   */
  storageName?: string;
  /**
   * instock
   */
  inquantity?: number;
  /**
   * instock
   */
  inTime?: string;
  /**
   * instock
   */
  operator?: string;
  /**
   * instock
   */
  status?: string;
}

export interface InstockQuery extends PageQuery {
  /**
   * instock
   */
  articleCode?: string;
  /**
   * instock
   */
  articleName?: string;
  /**
   * instock
   */
  storageName?: string;
  /**
   * instock
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
