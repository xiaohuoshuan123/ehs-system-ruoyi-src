export interface Safety_study_dtlVO {
  /**
   * safety_study_dtl
   */
  id: string | number;
  /**
   * safety_study_dtl
   */
  empCode: string;
  /**
   * safety_study_dtl
   */
  empName: string;
  /**
   * safety_study_dtl
   */
  studyTime: string;
}

export interface Safety_study_dtlForm extends BaseEntity {
  /**
   * safety_study_dtl
   */
  id?: string | number;
  /**
   * safety_study_dtl
   */
  studyMstId?: string | number;
  /**
   * safety_study_dtl
   */
  empCode?: string;
  /**
   * safety_study_dtl
   */
  empName?: string;
  /**
   * safety_study_dtl
   */
  studyStatus?: string;
  /**
   * safety_study_dtl
   */
  studyHours?: number;
  /**
   * safety_study_dtl
   */
  studyTime?: string;
}

export interface Safety_study_dtlQuery extends PageQuery {
  /**
   * safety_study_dtl
   */
  empCode?: string;
  /**
   * safety_study_dtl
   */
  empName?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
