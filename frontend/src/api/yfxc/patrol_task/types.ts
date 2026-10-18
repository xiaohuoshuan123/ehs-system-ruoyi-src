export interface Patrol_taskVO {
  /**
   * patrol_task
   */
  id: string | number;
  /**
   * patrol_task
   */
  checkOrgCodePath: string;
  /**
   * patrol_task
   */
  taskName: string;
  /**
   * patrol_task
   */
  taskTime: string;
  /**
   * patrol_task
   */
  checkerName: string;
  /**
   * patrol_task
   */
  status: string;
}

export interface Patrol_taskForm extends BaseEntity {
  /**
   * patrol_task
   */
  id?: string | number;
  /**
   * patrol_task
   */
  taskStatus?: string;
  /**
   * patrol_task
   */
  checkOrgCodePath?: string;
  /**
   * patrol_task
   */
  taskName?: string;
  /**
   * patrol_task
   */
  taskTime?: string;
  /**
   * patrol_task
   */
  checkerName?: string;
  /**
   * patrol_task
   */
  result?: string;
  /**
   * patrol_task
   */
  status?: string;
}

export interface Patrol_taskQuery extends PageQuery {
  /**
   * patrol_task
   */
  checkOrgCodePath?: string;
  /**
   * patrol_task
   */
  taskName?: string;
  /**
   * patrol_task
   */
  checkerName?: string;
  /**
   * patrol_task
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
