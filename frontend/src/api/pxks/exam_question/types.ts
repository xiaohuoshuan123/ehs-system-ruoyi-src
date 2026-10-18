export interface Exam_questionVO {
  /**
   * exam_question
   */
  id: string | number;
  /**
   * exam_question
   */
  status: string;
}

export interface Exam_questionForm extends BaseEntity {
  /**
   * exam_question
   */
  id?: string | number;
  /**
   * exam_question
   */
  bankTitle?: string;
  /**
   * exam_question
   */
  bankType?: string;
  /**
   * exam_question
   */
  questionsType?: string;
  /**
   * exam_question
   */
  questionStem?: string;
  /**
   * exam_question
   */
  optionA?: string;
  /**
   * exam_question
   */
  optionB?: string;
  /**
   * exam_question
   */
  optionC?: string;
  /**
   * exam_question
   */
  optionD?: string;
  /**
   * exam_question
   */
  answer?: string;
  /**
   * exam_question
   */
  score?: number;
  /**
   * exam_question
   */
  analysis?: string;
  /**
   * exam_question
   */
  status?: string;
}

export interface Exam_questionQuery extends PageQuery {
  /**
   * exam_question
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
