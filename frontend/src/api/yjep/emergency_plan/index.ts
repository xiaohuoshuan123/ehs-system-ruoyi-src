import type { Emergency_planForm, Emergency_planQuery, Emergency_planVO } from '@/api/yjep/emergency_plan/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EmergencyPlan列表
 * @param query
 * @returns {*}
 */
export const listEmergency_plan = (query?: Emergency_planQuery): AxiosPromise<PageResult<Emergency_planVO>> => {
  return request({
    url: '/yjep/emergency_plan/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EmergencyPlan详细
 * @param id
 */
export const getEmergency_plan = (id: string | number): AxiosPromise<Emergency_planVO> => {
  return request({
    url: '/yjep/emergency_plan/' + id,
    method: 'get'
  });
};

/**
 * 新增EmergencyPlan
 * @param data
 */
export const addEmergency_plan = (data: Emergency_planForm) => {
  return request({
    url: '/yjep/emergency_plan',
    method: 'post',
    data: data
  });
};

/**
 * 修改EmergencyPlan
 * @param data
 */
export const updateEmergency_plan = (data: Emergency_planForm) => {
  return request({
    url: '/yjep/emergency_plan',
    method: 'put',
    data: data
  });
};



/**
 * 删除EmergencyPlan
 * @param id
 */
export const delEmergency_plan = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yjep/emergency_plan/' + id,
    method: 'delete'
  });
};
