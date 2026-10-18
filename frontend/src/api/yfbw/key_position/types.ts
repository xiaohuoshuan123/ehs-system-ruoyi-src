export interface Key_positionVO {
  /**
   * key_position
   */
  id: string | number;
  /**
   * key_position
   */
  orgCodePath: string;
  /**
   * key_position
   */
  status: string;
}

export interface Key_positionForm extends BaseEntity {
  /**
   * key_position
   */
  id?: string | number;
  /**
   * key_position
   */
  positionTitle?: string;
  /**
   * key_position
   */
  specificLocation?: string;
  /**
   * key_position
   */
  orgCodePath?: string;
  /**
   * key_position
   */
  dutyContent?: string;
  /**
   * key_position
   */
  emergencyProcedure?: string;
  /**
   * key_position
   */
  status?: string;
}

export interface Key_positionQuery extends PageQuery {
  /**
   * key_position
   */
  orgCodePath?: string;
  /**
   * key_position
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
