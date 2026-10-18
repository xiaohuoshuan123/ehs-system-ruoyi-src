export interface Safety_costVO {
  /**
   * safety_cost
   */
  id: string | number;
  /**
   * safety_cost
   */
  orgCodePath: string;
  /**
   * safety_cost
   */
  costName: string;
  /**
   * safety_cost
   */
  status: string;
}

export interface Safety_costForm extends BaseEntity {
  /**
   * safety_cost
   */
  id?: string | number;
  /**
   * safety_cost
   */
  safetyCostId?: string | number;
  /**
   * safety_cost
   */
  orgCodePath?: string;
  /**
   * safety_cost
   */
  costName?: string;
  /**
   * safety_cost
   */
  costAmount?: number;
  /**
   * safety_cost
   */
  costYear?: string;
  /**
   * safety_cost
   */
  costMonth?: string;
  /**
   * safety_cost
   */
  status?: string;
}

export interface Safety_costQuery extends PageQuery {
  /**
   * safety_cost
   */
  orgCodePath?: string;
  /**
   * safety_cost
   */
  costName?: string;
  /**
   * safety_cost
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
