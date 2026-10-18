export interface StorageVO {
  /**
   * storage
   */
  id: string | number;
  /**
   * storage
   */
  storageName: string;
  /**
   * storage
   */
  storageCode: string;
  /**
   * storage
   */
  status: string;
}

export interface StorageForm extends BaseEntity {
  /**
   * storage
   */
  id?: string | number;
  /**
   * storage
   */
  storageName?: string;
  /**
   * storage
   */
  storageCode?: string;
  /**
   * storage
   */
  storageLocation?: string;
  /**
   * storage
   */
  capacity?: number;
  /**
   * storage
   */
  status?: string;
}

export interface StorageQuery extends PageQuery {
  /**
   * storage
   */
  storageName?: string;
  /**
   * storage
   */
  storageCode?: string;
  /**
   * storage
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
