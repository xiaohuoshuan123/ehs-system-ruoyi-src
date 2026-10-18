export interface ArchitectureVO {
  /**
   * architecture
   */
  id: string | number;
  /**
   * architecture
   */
  orgCodePath: string;
  /**
   * architecture
   */
  orgCode: string;
  /**
   * architecture
   */
  orgName: string;
  /**
   * architecture
   */
  parentCode: string;
  /**
   * architecture
   */
  status: string;
}

export interface ArchitectureForm extends BaseEntity {
  /**
   * architecture
   */
  id?: string | number;
  /**
   * architecture
   */
  orgCodePath?: string;
  /**
   * architecture
   */
  orgCode?: string;
  /**
   * architecture
   */
  orgName?: string;
  /**
   * architecture
   */
  parentCode?: string;
  /**
   * architecture
   */
  level?: number;
  /**
   * architecture
   */
  sortOrder?: number;
  /**
   * architecture
   */
  status?: string;
}

export interface ArchitectureQuery extends PageQuery {
  /**
   * architecture
   */
  orgCodePath?: string;
  /**
   * architecture
   */
  orgCode?: string;
  /**
   * architecture
   */
  orgName?: string;
  /**
   * architecture
   */
  parentCode?: string;
  /**
   * architecture
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
