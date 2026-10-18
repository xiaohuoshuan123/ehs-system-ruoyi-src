export interface Emergency_drillVO {
  /**
   * emergency_drill
   */
  id: string | number;
  /**
   * emergency_drill
   */
  emergencyName: string;
  /**
   * emergency_drill
   */
  orgCodes: string;
  /**
   * emergency_drill
   */
  drillTime: string;
  /**
   * emergency_drill
   */
  status: string;
}

export interface Emergency_drillForm extends BaseEntity {
  /**
   * emergency_drill
   */
  id?: string | number;
  /**
   * emergency_drill
   */
  emergencyName?: string;
  /**
   * emergency_drill
   */
  orgCodes?: string;
  /**
   * emergency_drill
   */
  drillType?: string;
  /**
   * emergency_drill
   */
  drillTime?: string;
  /**
   * emergency_drill
   */
  drillLocation?: string;
  /**
   * emergency_drill
   */
  drillContent?: string;
  /**
   * emergency_drill
   */
  drillResult?: string;
  /**
   * emergency_drill
   */
  status?: string;
}

export interface Emergency_drillQuery extends PageQuery {
  /**
   * emergency_drill
   */
  emergencyName?: string;
  /**
   * emergency_drill
   */
  orgCodes?: string;
  /**
   * emergency_drill
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
