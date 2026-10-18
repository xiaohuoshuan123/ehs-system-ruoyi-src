export interface Master_apprenticeVO {
  /**
   * master_apprentice
   */
  id: string | number;
  /**
   * master_apprentice
   */
  masterName: string;
  /**
   * master_apprentice
   */
  masterCode: string;
  /**
   * master_apprentice
   */
  apprenticeName: string;
  /**
   * master_apprentice
   */
  apprenticeCode: string;
  /**
   * master_apprentice
   */
  masterOrgCodePath: string;
  /**
   * master_apprentice
   */
  startDate: string;
  /**
   * master_apprentice
   */
  endDate: string;
  /**
   * master_apprentice
   */
  status: string;
}

export interface Master_apprenticeForm extends BaseEntity {
  /**
   * master_apprentice
   */
  id?: string | number;
  /**
   * master_apprentice
   */
  masterName?: string;
  /**
   * master_apprentice
   */
  masterCode?: string;
  /**
   * master_apprentice
   */
  apprenticeName?: string;
  /**
   * master_apprentice
   */
  apprenticeCode?: string;
  /**
   * master_apprentice
   */
  masterOrgCodePath?: string;
  /**
   * master_apprentice
   */
  startDate?: string;
  /**
   * master_apprentice
   */
  endDate?: string;
  /**
   * master_apprentice
   */
  status?: string;
}

export interface Master_apprenticeQuery extends PageQuery {
  /**
   * master_apprentice
   */
  masterName?: string;
  /**
   * master_apprentice
   */
  masterCode?: string;
  /**
   * master_apprentice
   */
  apprenticeName?: string;
  /**
   * master_apprentice
   */
  apprenticeCode?: string;
  /**
   * master_apprentice
   */
  masterOrgCodePath?: string;
  /**
   * master_apprentice
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
