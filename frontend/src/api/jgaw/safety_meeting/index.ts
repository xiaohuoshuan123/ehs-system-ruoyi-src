import type { Safety_meetingForm, Safety_meetingQuery, Safety_meetingVO } from '@/api/jgaw/safety_meeting/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SafetyMeeting列表
 * @param query
 * @returns {*}
 */
export const listSafety_meeting = (query?: Safety_meetingQuery): AxiosPromise<PageResult<Safety_meetingVO>> => {
  return request({
    url: '/jgaw/safety_meeting/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SafetyMeeting详细
 * @param id
 */
export const getSafety_meeting = (id: string | number): AxiosPromise<Safety_meetingVO> => {
  return request({
    url: '/jgaw/safety_meeting/' + id,
    method: 'get'
  });
};

/**
 * 新增SafetyMeeting
 * @param data
 */
export const addSafety_meeting = (data: Safety_meetingForm) => {
  return request({
    url: '/jgaw/safety_meeting',
    method: 'post',
    data: data
  });
};

/**
 * 修改SafetyMeeting
 * @param data
 */
export const updateSafety_meeting = (data: Safety_meetingForm) => {
  return request({
    url: '/jgaw/safety_meeting',
    method: 'put',
    data: data
  });
};



/**
 * 删除SafetyMeeting
 * @param id
 */
export const delSafety_meeting = (id: string | number | Array<string | number>) => {
  return request({
    url: '/jgaw/safety_meeting/' + id,
    method: 'delete'
  });
};
