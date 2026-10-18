export interface StockVO {
  /**
   * stock
   */
  id: string | number;
  /**
   * stock
   */
  storageName: string;
  /**
   * stock
   */
  articleName: string;
  /**
   * stock
   */
  articleCode: string;
  /**
   * stock
   */
  status: string;
}

export interface StockForm extends BaseEntity {
  /**
   * stock
   */
  id?: string | number;
  /**
   * stock
   */
  storageName?: string;
  /**
   * stock
   */
  articleName?: string;
  /**
   * stock
   */
  articleCode?: string;
  /**
   * stock
   */
  stockQuantity?: number;
  /**
   * stock
   */
  safetyStock?: number;
  /**
   * stock
   */
  status?: string;
}

export interface StockQuery extends PageQuery {
  /**
   * stock
   */
  storageName?: string;
  /**
   * stock
   */
  articleName?: string;
  /**
   * stock
   */
  articleCode?: string;
  /**
   * stock
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
