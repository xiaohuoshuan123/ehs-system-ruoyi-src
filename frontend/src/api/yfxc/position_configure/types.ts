export interface Position_configureVO {
  /**
   * position_configure
   */
  id: string | number;
  /**
   * position_configure
   */
  positionName: string;
  /**
   * position_configure
   */
  checkOrgCodePath: string;
  /**
   * position_configure
   */
  status: string;
}

export interface Position_configureForm extends BaseEntity {
  /**
   * position_configure
   */
  id?: string | number;
  /**
   * position_configure
   */
  positionName?: string;
  /**
   * position_configure
   */
  checkOrgCodePath?: string;
  /**
   * position_configure
   */
  checkCycle?: number;
  /**
   * position_configure
   */
  checkContent?: string;
  /**
   * position_configure
   */
  status?: string;
}

export interface Position_configureQuery extends PageQuery {
  /**
   * position_configure
   */
  positionName?: string;
  /**
   * position_configure
   */
  checkOrgCodePath?: string;
  /**
   * position_configure
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
