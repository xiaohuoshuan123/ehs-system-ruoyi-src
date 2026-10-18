export interface Emergency_teamVO {
  /**
   * emergency_team
   */
  id: string | number;
  /**
   * emergency_team
   */
  orgCodeId: string | number;
  /**
   * emergency_team
   */
  teamName: string;
  /**
   * emergency_team
   */
  status: string;
}

export interface Emergency_teamForm extends BaseEntity {
  /**
   * emergency_team
   */
  id?: string | number;
  /**
   * emergency_team
   */
  orgCodeId?: string | number;
  /**
   * emergency_team
   */
  teamName?: string;
  /**
   * emergency_team
   */
  teamLeader?: string;
  /**
   * emergency_team
   */
  teamPhone?: string;
  /**
   * emergency_team
   */
  teamMembers?: string;
  /**
   * emergency_team
   */
  teamType?: string;
  /**
   * emergency_team
   */
  status?: string;
}

export interface Emergency_teamQuery extends PageQuery {
  /**
   * emergency_team
   */
  orgCodeId?: string | number;
  /**
   * emergency_team
   */
  teamName?: string;
  /**
   * emergency_team
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
