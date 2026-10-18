export interface MsdsVO {
  /**
   * msds
   */
  id: string | number;
  /**
   * msds
   */
  articleCode: string;
  /**
   * msds
   */
  articleName: string;
  /**
   * msds
   */
  issueOrgCode: string;
  /**
   * msds
   */
  status: string;
}

export interface MsdsForm extends BaseEntity {
  /**
   * msds
   */
  id?: string | number;
  /**
   * msds
   */
  articleCode?: string;
  /**
   * msds
   */
  articleName?: string;
  /**
   * msds
   */
  issueOrgCode?: string;
  /**
   * msds
   */
  msdsContent?: string;
  /**
   * msds
   */
  attachmentIds?: string | number;
  /**
   * msds
   */
  status?: string;
}

export interface MsdsQuery extends PageQuery {
  /**
   * msds
   */
  articleCode?: string;
  /**
   * msds
   */
  articleName?: string;
  /**
   * msds
   */
  issueOrgCode?: string;
  /**
   * msds
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
