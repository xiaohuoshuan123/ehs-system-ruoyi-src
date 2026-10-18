export interface Safety_meetingVO {
  /**
   * safety_meeting
   */
  id: string | number;
  /**
   * safety_meeting
   */
  checkTimeBegin: string;
  /**
   * safety_meeting
   */
  checkTimeEnd: string;
  /**
   * safety_meeting
   */
  orgName: string;
  /**
   * safety_meeting
   */
  orgCodePath: string;
  /**
   * safety_meeting
   */
  status: string;
}

export interface Safety_meetingForm extends BaseEntity {
  /**
   * safety_meeting
   */
  id?: string | number;
  /**
   * safety_meeting
   */
  meetTypeValue?: string;
  /**
   * safety_meeting
   */
  checkTimeBegin?: string;
  /**
   * safety_meeting
   */
  checkTimeEnd?: string;
  /**
   * safety_meeting
   */
  orgName?: string;
  /**
   * safety_meeting
   */
  orgCodePath?: string;
  /**
   * safety_meeting
   */
  meetingTitle?: string;
  /**
   * safety_meeting
   */
  meetingContent?: string;
  /**
   * safety_meeting
   */
  attendees?: string;
  /**
   * safety_meeting
   */
  status?: string;
}

export interface Safety_meetingQuery extends PageQuery {
  /**
   * safety_meeting
   */
  orgName?: string;
  /**
   * safety_meeting
   */
  orgCodePath?: string;
  /**
   * safety_meeting
   */
  status?: string;
  /**
   * 日期范围参数
   */
  params?: any;
}
