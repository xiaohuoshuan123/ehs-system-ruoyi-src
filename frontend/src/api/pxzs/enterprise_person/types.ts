export interface Enterprise_personVO {
  /**
   * enterprise_person
   */
  id: string | number;
  /**
   * enterprise_person
   */
  empCode: string;
  /**
   * enterprise_person
   */
  empName: string;
  /**
   * enterprise_person
   */
  orgCodePath: string;
  /**
   * enterprise_person
   */
  orgCode: string;
  /**
   * enterprise_person
   */
  issueDate: string;
  /**
   * enterprise_person
   */
  expireDate: string;
  /**
   * enterprise_person
   */
  status: string;
}

export interface Enterprise_personForm extends BaseEntity {
  /**
   * enterprise_person
   */
  id?: string | number;
  /**
   * enterprise_person
   */
  empCode?: string;
  /**
   * enterprise_person
   */
  empName?: string;
  /**
   * enterprise_person
   */
  orgCodePath?: string;
  /**
   * enterprise_person
   */
  orgCode?: string;
  /**
   * enterprise_person
   */
  certificateTypeValue?: string;
  /**
   * enterprise_person
   */
  certificateNo?: string;
  /**
   * enterprise_person
   */
  issueDate?: string;
  /**
   * enterprise_person
   */
  expireDate?: string;
  /**
   * enterprise_person
   */
  flagStatus?: string;
  /**
   * enterprise_person
   */
  status?: string;
}

export interface Enterprise_personQuery extends PageQuery {
  /**
   * enterprise_person
   */
  empCode?: string;
  /**
   * enterprise_person
   */
  empName?: string;
  /**
   * enterprise_person
   */
  orgCodePath?: string;
  /**
   * enterprise_person
   */
  orgCode?: string;
  /**
   * enterprise_person
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
