export interface Law_performanceVO {
  /**
   * law_performance
   */
  id: string | number;
  /**
   * law_performance
   */
  endTime: string;
  /**
   * law_performance
   */
  orgCodePath: string;
  /**
   * law_performance
   */
  status: string;
}

export interface Law_performanceForm extends BaseEntity {
  /**
   * law_performance
   */
  id?: string | number;
  /**
   * law_performance
   */
  radiolabel?: string;
  /**
   * law_performance
   */
  performanceStatus?: string;
  /**
   * law_performance
   */
  endTime?: string;
  /**
   * law_performance
   */
  periods?: string;
  /**
   * law_performance
   */
  orgCodePath?: string;
  /**
   * law_performance
   */
  status?: string;
}

export interface Law_performanceQuery extends PageQuery {
  /**
   * law_performance
   */
  orgCodePath?: string;
  /**
   * law_performance
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
