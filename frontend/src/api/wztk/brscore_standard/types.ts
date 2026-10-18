export interface Brscore_standardVO {
  /**
   * brscore_standard
   */
  id: string | number;
  /**
   * brscore_standard
   */
  orgCode: string;
  /**
   * brscore_standard
   */
  orgCodePath: string;
  /**
   * brscore_standard
   */
  status: string;
}

export interface Brscore_standardForm extends BaseEntity {
  /**
   * brscore_standard
   */
  id?: string | number;
  /**
   * brscore_standard
   */
  standardContent?: string;
  /**
   * brscore_standard
   */
  orgCode?: string;
  /**
   * brscore_standard
   */
  orgCodePath?: string;
  /**
   * brscore_standard
   */
  status?: string;
}

export interface Brscore_standardQuery extends PageQuery {
  /**
   * brscore_standard
   */
  orgCode?: string;
  /**
   * brscore_standard
   */
  orgCodePath?: string;
  /**
   * brscore_standard
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
