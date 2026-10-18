export interface Compliance_evaluationVO {
  /**
   * compliance_evaluation
   */
  id: string | number;
  /**
   * compliance_evaluation
   */
  manageOrgCode: string;
  /**
   * compliance_evaluation
   */
  evaluateDate: string;
  /**
   * compliance_evaluation
   */
  status: string;
}

export interface Compliance_evaluationForm extends BaseEntity {
  /**
   * compliance_evaluation
   */
  id?: string | number;
  /**
   * compliance_evaluation
   */
  manageOrgCode?: string;
  /**
   * compliance_evaluation
   */
  year?: string;
  /**
   * compliance_evaluation
   */
  evaluationResult?: string;
  /**
   * compliance_evaluation
   */
  evaluator?: string;
  /**
   * compliance_evaluation
   */
  evaluateDate?: string;
  /**
   * compliance_evaluation
   */
  status?: string;
}

export interface Compliance_evaluationQuery extends PageQuery {
  /**
   * compliance_evaluation
   */
  manageOrgCode?: string;
  /**
   * compliance_evaluation
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
