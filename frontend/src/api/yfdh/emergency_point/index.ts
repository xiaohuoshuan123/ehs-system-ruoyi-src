import type { Emergency_pointForm, Emergency_pointQuery, Emergency_pointVO } from '@/api/yfdh/emergency_point/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EmergencyPoint列表
 * @param query
 * @returns {*}
 */
export const listEmergency_point = (query?: Emergency_pointQuery): AxiosPromise<PageResult<Emergency_pointVO>> => {
  return request({
    url: '/yfdh/emergency_point/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EmergencyPoint详细
 * @param id
 */
export const getEmergency_point = (id: string | number): AxiosPromise<Emergency_pointVO> => {
  return request({
    url: '/yfdh/emergency_point/' + id,
    method: 'get'
  });
};

/**
 * 新增EmergencyPoint
 * @param data
 */
export const addEmergency_point = (data: Emergency_pointForm) => {
  return request({
    url: '/yfdh/emergency_point',
    method: 'post',
    data: data
  });
};

/**
 * 修改EmergencyPoint
 * @param data
 */
export const updateEmergency_point = (data: Emergency_pointForm) => {
  return request({
    url: '/yfdh/emergency_point',
    method: 'put',
    data: data
  });
};



/**
 * 删除EmergencyPoint
 * @param id
 */
export const delEmergency_point = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yfdh/emergency_point/' + id,
    method: 'delete'
  });
};
