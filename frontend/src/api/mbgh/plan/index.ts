import type { PlanForm, PlanQuery, PlanVO } from '@/api/mbgh/plan/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Plan列表
 * @param query
 * @returns {*}
 */
export const listPlan = (query?: PlanQuery): AxiosPromise<PageResult<PlanVO>> => {
  return request({
    url: '/mbgh/plan/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Plan详细
 * @param id
 */
export const getPlan = (id: string | number): AxiosPromise<PlanVO> => {
  return request({
    url: '/mbgh/plan/' + id,
    method: 'get'
  });
};

/**
 * 新增Plan
 * @param data
 */
export const addPlan = (data: PlanForm) => {
  return request({
    url: '/mbgh/plan',
    method: 'post',
    data: data
  });
};

/**
 * 修改Plan
 * @param data
 */
export const updatePlan = (data: PlanForm) => {
  return request({
    url: '/mbgh/plan',
    method: 'put',
    data: data
  });
};



/**
 * 删除Plan
 * @param id
 */
export const delPlan = (id: string | number | Array<string | number>) => {
  return request({
    url: '/mbgh/plan/' + id,
    method: 'delete'
  });
};
