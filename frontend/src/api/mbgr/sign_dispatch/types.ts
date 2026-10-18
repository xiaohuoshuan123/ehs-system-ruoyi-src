export interface Sign_dispatchVO {
  /**
   * sign_dispatch
   */
  id: string | number;
  /**
   * sign_dispatch
   */
  empName: string;
  /**
   * sign_dispatch
   */
  status: string;
}

export interface Sign_dispatchForm extends BaseEntity {
  /**
   * sign_dispatch
   */
  id?: string | number;
  /**
   * sign_dispatch
   */
  templateId?: string | number;
  /**
   * sign_dispatch
   */
  dispatchYear?: string;
  /**
   * sign_dispatch
   */
  targetId?: string | number;
  /**
   * sign_dispatch
   */
  empName?: string;
  /**
   * sign_dispatch
   */
  dictId?: string | number;
  /**
   * sign_dispatch
   */
  dispatchStatus?: string;
  /**
   * sign_dispatch
   */
  signStatus?: string;
  /**
   * sign_dispatch
   */
  status?: string;
}

export interface Sign_dispatchQuery extends PageQuery {
  /**
   * sign_dispatch
   */
  empName?: string;
  /**
   * sign_dispatch
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
