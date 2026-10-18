export interface Standard_deptVO {
  /**
   * standard_dept
   */
  id: string | number;
  /**
   * standard_dept
   */
  orgCodePath: string;
  /**
   * standard_dept
   */
  deptName: string;
  /**
   * standard_dept
   */
  deptCode: string;
  /**
   * standard_dept
   */
  status: string;
}

export interface Standard_deptForm extends BaseEntity {
  /**
   * standard_dept
   */
  id?: string | number;
  /**
   * standard_dept
   */
  keyword?: string;
  /**
   * standard_dept
   */
  orgCodePath?: string;
  /**
   * standard_dept
   */
  deptName?: string;
  /**
   * standard_dept
   */
  deptCode?: string;
  /**
   * standard_dept
   */
  status?: string;
}

export interface Standard_deptQuery extends PageQuery {
  /**
   * standard_dept
   */
  orgCodePath?: string;
  /**
   * standard_dept
   */
  deptName?: string;
  /**
   * standard_dept
   */
  deptCode?: string;
  /**
   * standard_dept
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
