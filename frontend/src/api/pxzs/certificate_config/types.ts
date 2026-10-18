export interface Certificate_configVO {
  /**
   * certificate_config
   */
  id: string | number;
  /**
   * certificate_config
   */
  status: string;
}

export interface Certificate_configForm extends BaseEntity {
  /**
   * certificate_config
   */
  id?: string | number;
  /**
   * certificate_config
   */
  certificateTypeValue?: string;
  /**
   * certificate_config
   */
  title?: string;
  /**
   * certificate_config
   */
  validDays?: string | number;
  /**
   * certificate_config
   */
  expireWarningDays?: number;
  /**
   * certificate_config
   */
  status?: string;
}

export interface Certificate_configQuery extends PageQuery {
  /**
   * certificate_config
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
