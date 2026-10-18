export interface Safety_dutyVO {
  /**
   * safety_duty
   */
  id: string | number;
  /**
   * safety_duty
   */
  orgCodePath: string;
  /**
   * safety_duty
   */
  orgCode: string;
  /**
   * safety_duty
   */
  orgName: string;
  /**
   * safety_duty
   */
  jobName: string;
  /**
   * safety_duty
   */
  status: string;
}

export interface Safety_dutyForm extends BaseEntity {
  /**
   * safety_duty
   */
  id?: string | number;
  /**
   * safety_duty
   */
  orgCodePath?: string;
  /**
   * safety_duty
   */
  orgCode?: string;
  /**
   * safety_duty
   */
  orgName?: string;
  /**
   * safety_duty
   */
  jobName?: string;
  /**
   * safety_duty
   */
  title?: string;
  /**
   * safety_duty
   */
  maintenance?: string;
  /**
   * safety_duty
   */
  dutyContent?: string;
  /**
   * safety_duty
   */
  status?: string;
}

export interface Safety_dutyQuery extends PageQuery {
  /**
   * safety_duty
   */
  orgCodePath?: string;
  /**
   * safety_duty
   */
  orgCode?: string;
  /**
   * safety_duty
   */
  orgName?: string;
  /**
   * safety_duty
   */
  jobName?: string;
  /**
   * safety_duty
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
