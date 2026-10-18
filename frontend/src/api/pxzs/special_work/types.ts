export interface Special_workVO {
  /**
   * special_work
   */
  id: string | number;
  /**
   * special_work
   */
  empCode: string;
  /**
   * special_work
   */
  empName: string;
  /**
   * special_work
   */
  orgCodePath: string;
  /**
   * special_work
   */
  issueDate: string;
  /**
   * special_work
   */
  expireDate: string;
  /**
   * special_work
   */
  status: string;
}

export interface Special_workForm extends BaseEntity {
  /**
   * special_work
   */
  id?: string | number;
  /**
   * special_work
   */
  empCode?: string;
  /**
   * special_work
   */
  empName?: string;
  /**
   * special_work
   */
  certificateClassValue?: string;
  /**
   * special_work
   */
  certificateNo?: string;
  /**
   * special_work
   */
  orgCodePath?: string;
  /**
   * special_work
   */
  issueDate?: string;
  /**
   * special_work
   */
  expireDate?: string;
  /**
   * special_work
   */
  flagStatus?: string;
  /**
   * special_work
   */
  status?: string;
}

export interface Special_workQuery extends PageQuery {
  /**
   * special_work
   */
  empCode?: string;
  /**
   * special_work
   */
  empName?: string;
  /**
   * special_work
   */
  orgCodePath?: string;
  /**
   * special_work
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
