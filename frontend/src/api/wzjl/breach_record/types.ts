export interface Breach_recordVO {
  /**
   * breach_record
   */
  id: string | number;
  /**
   * breach_record
   */
  checkOrgCode: string;
  /**
   * breach_record
   */
  checkOrgCodePath: string;
  /**
   * breach_record
   */
  breachTime: string;
  /**
   * breach_record
   */
  status: string;
}

export interface Breach_recordForm extends BaseEntity {
  /**
   * breach_record
   */
  id?: string | number;
  /**
   * breach_record
   */
  checkOrgCode?: string;
  /**
   * breach_record
   */
  checkOrgCodePath?: string;
  /**
   * breach_record
   */
  breachEmp?: string;
  /**
   * breach_record
   */
  breachTime?: string;
  /**
   * breach_record
   */
  breachContent?: string;
  /**
   * breach_record
   */
  breachType?: string;
  /**
   * breach_record
   */
  options?: string;
  /**
   * breach_record
   */
  status?: string;
}

export interface Breach_recordQuery extends PageQuery {
  /**
   * breach_record
   */
  checkOrgCode?: string;
  /**
   * breach_record
   */
  checkOrgCodePath?: string;
  /**
   * breach_record
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
