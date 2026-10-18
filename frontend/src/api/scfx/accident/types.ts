export interface AccidentVO {
  /**
   * accident
   */
  id: string | number;
  /**
   * accident
   */
  theirOrgCode: string;
  /**
   * accident
   */
  accidentName: string | number;
  /**
   * accident
   */
  accidentTime: string | number;
  /**
   * accident
   */
  status: string;
}

export interface AccidentForm extends BaseEntity {
  /**
   * accident
   */
  id?: string | number;
  /**
   * accident
   */
  theirOrgCode?: string;
  /**
   * accident
   */
  accidentName?: string | number;
  /**
   * accident
   */
  accidentTime?: string | number;
  /**
   * accident
   */
  accidentType?: string | number;
  /**
   * accident
   */
  accidentDesc?: string | number;
  /**
   * accident
   */
  accidentLoss?: string | number;
  /**
   * accident
   */
  status?: string;
}

export interface AccidentQuery extends PageQuery {
  /**
   * accident
   */
  theirOrgCode?: string;
  /**
   * accident
   */
  accidentName?: string | number;
  /**
   * accident
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
