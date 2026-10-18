export interface LawsVO {
  /**
   * laws
   */
  id: string | number;
  /**
   * laws
   */
  publishDate: string;
  /**
   * laws
   */
  fileName: string;
  /**
   * laws
   */
  orgCode: string;
  /**
   * laws
   */
  orgName: string;
  /**
   * laws
   */
  status: string;
}

export interface LawsForm extends BaseEntity {
  /**
   * laws
   */
  id?: string | number;
  /**
   * laws
   */
  publishDate?: string;
  /**
   * laws
   */
  fileNo?: string;
  /**
   * laws
   */
  fileName?: string;
  /**
   * laws
   */
  title?: string;
  /**
   * laws
   */
  lawType?: string;
  /**
   * laws
   */
  lawsDataStatus?: string;
  /**
   * laws
   */
  effectStatus?: string;
  /**
   * laws
   */
  orgCode?: string;
  /**
   * laws
   */
  orgName?: string;
  /**
   * laws
   */
  content?: string;
  /**
   * laws
   */
  attachmentIds?: string | number;
  /**
   * laws
   */
  status?: string;
}

export interface LawsQuery extends PageQuery {
  /**
   * laws
   */
  fileName?: string;
  /**
   * laws
   */
  orgCode?: string;
  /**
   * laws
   */
  orgName?: string;
  /**
   * laws
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
