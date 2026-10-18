export interface Law_modelVO {
  /**
   * law_model
   */
  id: string | number;
  /**
   * law_model
   */
  targetCode: string;
  /**
   * law_model
   */
  secendModuleName: string;
  /**
   * law_model
   */
  status: string;
}

export interface Law_modelForm extends BaseEntity {
  /**
   * law_model
   */
  id?: string | number;
  /**
   * law_model
   */
  moduleValue?: string;
  /**
   * law_model
   */
  targetCode?: string;
  /**
   * law_model
   */
  secendModuleName?: string;
  /**
   * law_model
   */
  modelContent?: string;
  /**
   * law_model
   */
  status?: string;
}

export interface Law_modelQuery extends PageQuery {
  /**
   * law_model
   */
  targetCode?: string;
  /**
   * law_model
   */
  secendModuleName?: string;
  /**
   * law_model
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
