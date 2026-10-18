export interface Emergency_materialVO {
  /**
   * emergency_material
   */
  id: string | number;
  /**
   * emergency_material
   */
  orgCodeId: string | number;
  /**
   * emergency_material
   */
  materialName: string;
  /**
   * emergency_material
   */
  checkDate: string;
  /**
   * emergency_material
   */
  status: string;
}

export interface Emergency_materialForm extends BaseEntity {
  /**
   * emergency_material
   */
  id?: string | number;
  /**
   * emergency_material
   */
  orgCodeId?: string | number;
  /**
   * emergency_material
   */
  materialName?: string;
  /**
   * emergency_material
   */
  materialType?: string;
  /**
   * emergency_material
   */
  materialQuantity?: number;
  /**
   * emergency_material
   */
  materialLocation?: string;
  /**
   * emergency_material
   */
  checkDate?: string;
  /**
   * emergency_material
   */
  status?: string;
}

export interface Emergency_materialQuery extends PageQuery {
  /**
   * emergency_material
   */
  orgCodeId?: string | number;
  /**
   * emergency_material
   */
  materialName?: string;
  /**
   * emergency_material
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
