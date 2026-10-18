export interface Risk_control_listVO {
  /**
   * risk_control_list
   */
  id: string | number;
  /**
   * risk_control_list
   */
  riskName: string;
  /**
   * risk_control_list
   */
  status: string;
}

export interface Risk_control_listForm extends BaseEntity {
  /**
   * risk_control_list
   */
  id?: string | number;
  /**
   * risk_control_list
   */
  orgRecFlag?: string;
  /**
   * risk_control_list
   */
  workunitId?: string | number;
  /**
   * risk_control_list
   */
  riskName?: string;
  /**
   * risk_control_list
   */
  riskLevel?: string;
  /**
   * risk_control_list
   */
  controlMeasure?: string;
  /**
   * risk_control_list
   */
  status?: string;
}

export interface Risk_control_listQuery extends PageQuery {
  /**
   * risk_control_list
   */
  riskName?: string;
  /**
   * risk_control_list
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
