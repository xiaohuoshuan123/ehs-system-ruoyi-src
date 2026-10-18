export interface Sign_templateVO {
  /**
   * sign_template
   */
  id: string | number;
  /**
   * sign_template
   */
  templateName: string;
  /**
   * sign_template
   */
  status: string;
}

export interface Sign_templateForm extends BaseEntity {
  /**
   * sign_template
   */
  id?: string | number;
  /**
   * sign_template
   */
  templateName?: string;
  /**
   * sign_template
   */
  applyPosition?: string;
  /**
   * sign_template
   */
  templateContent?: string;
  /**
   * sign_template
   */
  status?: string;
}

export interface Sign_templateQuery extends PageQuery {
  /**
   * sign_template
   */
  templateName?: string;
  /**
   * sign_template
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
