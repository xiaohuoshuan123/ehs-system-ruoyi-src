import type { Emergency_teamForm, Emergency_teamQuery, Emergency_teamVO } from '@/api/yjtm/emergency_team/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EmergencyTeam列表
 * @param query
 * @returns {*}
 */
export const listEmergency_team = (query?: Emergency_teamQuery): AxiosPromise<PageResult<Emergency_teamVO>> => {
  return request({
    url: '/yjtm/emergency_team/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EmergencyTeam详细
 * @param id
 */
export const getEmergency_team = (id: string | number): AxiosPromise<Emergency_teamVO> => {
  return request({
    url: '/yjtm/emergency_team/' + id,
    method: 'get'
  });
};

/**
 * 新增EmergencyTeam
 * @param data
 */
export const addEmergency_team = (data: Emergency_teamForm) => {
  return request({
    url: '/yjtm/emergency_team',
    method: 'post',
    data: data
  });
};

/**
 * 修改EmergencyTeam
 * @param data
 */
export const updateEmergency_team = (data: Emergency_teamForm) => {
  return request({
    url: '/yjtm/emergency_team',
    method: 'put',
    data: data
  });
};



/**
 * 删除EmergencyTeam
 * @param id
 */
export const delEmergency_team = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yjtm/emergency_team/' + id,
    method: 'delete'
  });
};
