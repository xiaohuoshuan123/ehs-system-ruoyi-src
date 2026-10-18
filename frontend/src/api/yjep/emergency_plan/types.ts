export interface Emergency_planVO {
  /**
   * emergency_plan
   */
  id: string | number;
  /**
   * emergency_plan
   */
  emerName: string;
  /**
   * emergency_plan
   */
  orgCodes: string;
  /**
   * emergency_plan
   */
  reviewDate: string;
  /**
   * emergency_plan
   */
  effectiveDate: string;
  /**
   * emergency_plan
   */
  status: string;
}

export interface Emergency_planForm extends BaseEntity {
  /**
   * emergency_plan
   */
  id?: string | number;
  /**
   * emergency_plan
   */
  emerName?: string;
  /**
   * emergency_plan
   */
  orgCodes?: string;
  /**
   * emergency_plan
   */
  emerType?: string;
  /**
   * emergency_plan
   */
  emergencyGrade?: string;
  /**
   * emergency_plan
   */
  emerContent?: string;
  /**
   * emergency_plan
   */
  emerLeader?: string;
  /**
   * emergency_plan
   */
  emerPhone?: string;
  /**
   * emergency_plan
   */
  reviewDate?: string;
  /**
   * emergency_plan
   */
  effectiveDate?: string;
  /**
   * emergency_plan
   */
  status?: string;
}

export interface Emergency_planQuery extends PageQuery {
  /**
   * emergency_plan
   */
  emerName?: string;
  /**
   * emergency_plan
   */
  orgCodes?: string;
  /**
   * emergency_plan
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
