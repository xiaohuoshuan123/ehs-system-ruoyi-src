export interface PlanVO {
  /**
   * plan
   */
  id: string | number;
  /**
   * plan
   */
  orgCode: string;
  /**
   * plan
   */
  orgCodePath: string;
  /**
   * plan
   */
  status: string;
}

export interface PlanForm extends BaseEntity {
  /**
   * plan
   */
  id?: string | number;
  /**
   * plan
   */
  orgCode?: string;
  /**
   * plan
   */
  orgCodePath?: string;
  /**
   * plan
   */
  planYear?: string;
  /**
   * plan
   */
  targetType?: string;
  /**
   * plan
   */
  treeData?: string;
  /**
   * plan
   */
  planContent?: string;
  /**
   * plan
   */
  status?: string;
}

export interface PlanQuery extends PageQuery {
  /**
   * plan
   */
  orgCode?: string;
  /**
   * plan
   */
  orgCodePath?: string;
  /**
   * plan
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
