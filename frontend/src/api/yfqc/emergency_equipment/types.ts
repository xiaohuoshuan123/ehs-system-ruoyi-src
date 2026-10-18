export interface Emergency_equipmentVO {
  /**
   * emergency_equipment
   */
  id: string | number;
  /**
   * emergency_equipment
   */
  equipmentName: string;
  /**
   * emergency_equipment
   */
  lastCheckDate: string;
  /**
   * emergency_equipment
   */
  nextCheckDate: string;
  /**
   * emergency_equipment
   */
  status: string;
}

export interface Emergency_equipmentForm extends BaseEntity {
  /**
   * emergency_equipment
   */
  id?: string | number;
  /**
   * emergency_equipment
   */
  equipmentName?: string;
  /**
   * emergency_equipment
   */
  fireControlType?: string;
  /**
   * emergency_equipment
   */
  equipmentModel?: string;
  /**
   * emergency_equipment
   */
  equipmentLocation?: string;
  /**
   * emergency_equipment
   */
  checkCycle?: number;
  /**
   * emergency_equipment
   */
  lastCheckDate?: string;
  /**
   * emergency_equipment
   */
  nextCheckDate?: string;
  /**
   * emergency_equipment
   */
  status?: string;
}

export interface Emergency_equipmentQuery extends PageQuery {
  /**
   * emergency_equipment
   */
  equipmentName?: string;
  /**
   * emergency_equipment
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
