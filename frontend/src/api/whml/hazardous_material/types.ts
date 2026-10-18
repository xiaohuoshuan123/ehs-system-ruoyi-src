export interface Hazardous_materialVO {
  /**
   * hazardous_material
   */
  id: string | number;
  /**
   * hazardous_material
   */
  articleCode: string;
  /**
   * hazardous_material
   */
  articleName: string;
  /**
   * hazardous_material
   */
  orgCodePath: string;
  /**
   * hazardous_material
   */
  status: string;
}

export interface Hazardous_materialForm extends BaseEntity {
  /**
   * hazardous_material
   */
  id?: string | number;
  /**
   * hazardous_material
   */
  type?: string;
  /**
   * hazardous_material
   */
  articleCode?: string;
  /**
   * hazardous_material
   */
  articleName?: string;
  /**
   * hazardous_material
   */
  iifSuperviseChemicals?: string;
  /**
   * hazardous_material
   */
  orgCodePath?: string;
  /**
   * hazardous_material
   */
  storageLocation?: string;
  /**
   * hazardous_material
   */
  quantity?: number;
  /**
   * hazardous_material
   */
  unit?: string;
  /**
   * hazardous_material
   */
  status?: string;
}

export interface Hazardous_materialQuery extends PageQuery {
  /**
   * hazardous_material
   */
  articleCode?: string;
  /**
   * hazardous_material
   */
  articleName?: string;
  /**
   * hazardous_material
   */
  orgCodePath?: string;
  /**
   * hazardous_material
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
