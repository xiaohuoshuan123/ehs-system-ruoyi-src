import type { Safety_costForm, Safety_costQuery, Safety_costVO } from '@/api/jgfy/safety_cost/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SafetyCost列表
 * @param query
 * @returns {*}
 */
export const listSafety_cost = (query?: Safety_costQuery): AxiosPromise<PageResult<Safety_costVO>> => {
  return request({
    url: '/jgfy/safety_cost/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SafetyCost详细
 * @param id
 */
export const getSafety_cost = (id: string | number): AxiosPromise<Safety_costVO> => {
  return request({
    url: '/jgfy/safety_cost/' + id,
    method: 'get'
  });
};

/**
 * 新增SafetyCost
 * @param data
 */
export const addSafety_cost = (data: Safety_costForm) => {
  return request({
    url: '/jgfy/safety_cost',
    method: 'post',
    data: data
  });
};

/**
 * 修改SafetyCost
 * @param data
 */
export const updateSafety_cost = (data: Safety_costForm) => {
  return request({
    url: '/jgfy/safety_cost',
    method: 'put',
    data: data
  });
};



/**
 * 删除SafetyCost
 * @param id
 */
export const delSafety_cost = (id: string | number | Array<string | number>) => {
  return request({
    url: '/jgfy/safety_cost/' + id,
    method: 'delete'
  });
};
