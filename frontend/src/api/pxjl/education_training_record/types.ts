export interface Education_training_recordVO {
  /**
   * education_training_record
   */
  id: string | number;
  /**
   * education_training_record
   */
  orgCode1: string;
  /**
   * education_training_record
   */
  empCode: string;
  /**
   * education_training_record
   */
  empName: string;
  /**
   * education_training_record
   */
  status: string;
}

export interface Education_training_recordForm extends BaseEntity {
  /**
   * education_training_record
   */
  id?: string | number;
  /**
   * education_training_record
   */
  mstId?: string | number;
  /**
   * education_training_record
   */
  orgCode1?: string;
  /**
   * education_training_record
   */
  trainYear?: string;
  /**
   * education_training_record
   */
  trainType?: string;
  /**
   * education_training_record
   */
  empCode?: string;
  /**
   * education_training_record
   */
  empName?: string;
  /**
   * education_training_record
   */
  trainResult?: string;
  /**
   * education_training_record
   */
  score?: number;
  /**
   * education_training_record
   */
  status?: string;
}

export interface Education_training_recordQuery extends PageQuery {
  /**
   * education_training_record
   */
  orgCode1?: string;
  /**
   * education_training_record
   */
  empCode?: string;
  /**
   * education_training_record
   */
  empName?: string;
  /**
   * education_training_record
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
