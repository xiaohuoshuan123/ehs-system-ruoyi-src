export interface Safety_study_mstVO {
  /**
   * safety_study_mst
   */
  id: string | number;
  /**
   * safety_study_mst
   */
  status: string;
  /**
   * safety_study_mst
   */
  publishTime: string;
}

export interface Safety_study_mstForm extends BaseEntity {
  /**
   * safety_study_mst
   */
  id?: string | number;
  /**
   * safety_study_mst
   */
  materialsTitle?: string;
  /**
   * safety_study_mst
   */
  status?: string;
  /**
   * safety_study_mst
   */
  ifMustLearn?: string;
  /**
   * safety_study_mst
   */
  studyHours?: number;
  /**
   * safety_study_mst
   */
  publisher?: string;
  /**
   * safety_study_mst
   */
  publishTime?: string;
}

export interface Safety_study_mstQuery extends PageQuery {
  /**
   * safety_study_mst
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
