export interface Education_training_mstVO {
  /**
   * education_training_mst
   */
  id: string | number;
  /**
   * education_training_mst
   */
  orgCode: string;
  /**
   * education_training_mst
   */
  orgCodePath: string;
  /**
   * education_training_mst
   */
  trainName: string;
  /**
   * education_training_mst
   */
  trainStartTime: string;
  /**
   * education_training_mst
   */
  trainEndTime: string;
  /**
   * education_training_mst
   */
  status: string;
}

export interface Education_training_mstForm extends BaseEntity {
  /**
   * education_training_mst
   */
  id?: string | number;
  /**
   * education_training_mst
   */
  trainYear?: string;
  /**
   * education_training_mst
   */
  orgCode?: string;
  /**
   * education_training_mst
   */
  orgCodePath?: string;
  /**
   * education_training_mst
   */
  trainName?: string;
  /**
   * education_training_mst
   */
  trainType?: string;
  /**
   * education_training_mst
   */
  trainContent?: string;
  /**
   * education_training_mst
   */
  trainStartTime?: string;
  /**
   * education_training_mst
   */
  trainEndTime?: string;
  /**
   * education_training_mst
   */
  trainer?: string;
  /**
   * education_training_mst
   */
  participantCount?: number;
  /**
   * education_training_mst
   */
  status?: string;
}

export interface Education_training_mstQuery extends PageQuery {
  /**
   * education_training_mst
   */
  orgCode?: string;
  /**
   * education_training_mst
   */
  orgCodePath?: string;
  /**
   * education_training_mst
   */
  trainName?: string;
  /**
   * education_training_mst
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
