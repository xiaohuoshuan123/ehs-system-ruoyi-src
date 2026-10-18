export interface Emergency_pointVO {
  /**
   * emergency_point
   */
  id: string | number;
  /**
   * emergency_point
   */
  dutyOrgCode: string;
  /**
   * emergency_point
   */
  pointName: string;
  /**
   * emergency_point
   */
  status: string;
}

export interface Emergency_pointForm extends BaseEntity {
  /**
   * emergency_point
   */
  id?: string | number;
  /**
   * emergency_point
   */
  dutyOrgCode?: string;
  /**
   * emergency_point
   */
  pointCoordinate?: string;
  /**
   * emergency_point
   */
  pointName?: string;
  /**
   * emergency_point
   */
  pointType?: string;
  /**
   * emergency_point
   */
  pointContent?: string;
  /**
   * emergency_point
   */
  status?: string;
}

export interface Emergency_pointQuery extends PageQuery {
  /**
   * emergency_point
   */
  dutyOrgCode?: string;
  /**
   * emergency_point
   */
  pointName?: string;
  /**
   * emergency_point
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
