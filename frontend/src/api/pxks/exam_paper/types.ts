export interface Exam_paperVO {
  /**
   * exam_paper
   */
  id: string | number;
  /**
   * exam_paper
   */
  examName: string;
  /**
   * exam_paper
   */
  status: string;
}

export interface Exam_paperForm extends BaseEntity {
  /**
   * exam_paper
   */
  id?: string | number;
  /**
   * exam_paper
   */
  examName?: string;
  /**
   * exam_paper
   */
  status?: string;
  /**
   * exam_paper
   */
  makeUpCount?: number;
  /**
   * exam_paper
   */
  totalScore?: number;
  /**
   * exam_paper
   */
  passScore?: number;
  /**
   * exam_paper
   */
  duration?: number;
}

export interface Exam_paperQuery extends PageQuery {
  /**
   * exam_paper
   */
  examName?: string;
  /**
   * exam_paper
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
