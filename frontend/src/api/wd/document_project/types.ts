export interface Document_projectVO {
  /**
   * document_project
   */
  id: string | number;
  /**
   * document_project
   */
  dutyOrgCode: string;
  /**
   * document_project
   */
  projectName: string;
  /**
   * document_project
   */
  status: string;
}

export interface Document_projectForm extends BaseEntity {
  /**
   * document_project
   */
  id?: string | number;
  /**
   * document_project
   */
  dutyOrgCode?: string;
  /**
   * document_project
   */
  projectName?: string;
  /**
   * document_project
   */
  projectDesc?: string;
  /**
   * document_project
   */
  status?: string;
}

export interface Document_projectQuery extends PageQuery {
  /**
   * document_project
   */
  dutyOrgCode?: string;
  /**
   * document_project
   */
  projectName?: string;
  /**
   * document_project
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
