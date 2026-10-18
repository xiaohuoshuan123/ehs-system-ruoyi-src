export interface Company_regulationVO {
  /**
   * company_regulation
   */
  id: string | number;
  /**
   * company_regulation
   */
  manageOrgCode: string;
  /**
   * company_regulation
   */
  manageOrgName: string;
  /**
   * company_regulation
   */
  status: string;
}

export interface Company_regulationForm extends BaseEntity {
  /**
   * company_regulation
   */
  id?: string | number;
  /**
   * company_regulation
   */
  manageOrgCode?: string;
  /**
   * company_regulation
   */
  manageOrgName?: string;
  /**
   * company_regulation
   */
  lawRegulations?: string;
  /**
   * company_regulation
   */
  status?: string;
}

export interface Company_regulationQuery extends PageQuery {
  /**
   * company_regulation
   */
  manageOrgCode?: string;
  /**
   * company_regulation
   */
  manageOrgName?: string;
  /**
   * company_regulation
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
