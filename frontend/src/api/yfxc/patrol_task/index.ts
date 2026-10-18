import type { Patrol_taskForm, Patrol_taskQuery, Patrol_taskVO } from '@/api/yfxc/patrol_task/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询PatrolTask列表
 * @param query
 * @returns {*}
 */
export const listPatrol_task = (query?: Patrol_taskQuery): AxiosPromise<PageResult<Patrol_taskVO>> => {
  return request({
    url: '/yfxc/patrol_task/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询PatrolTask详细
 * @param id
 */
export const getPatrol_task = (id: string | number): AxiosPromise<Patrol_taskVO> => {
  return request({
    url: '/yfxc/patrol_task/' + id,
    method: 'get'
  });
};

/**
 * 新增PatrolTask
 * @param data
 */
export const addPatrol_task = (data: Patrol_taskForm) => {
  return request({
    url: '/yfxc/patrol_task',
    method: 'post',
    data: data
  });
};

/**
 * 修改PatrolTask
 * @param data
 */
export const updatePatrol_task = (data: Patrol_taskForm) => {
  return request({
    url: '/yfxc/patrol_task',
    method: 'put',
    data: data
  });
};



/**
 * 删除PatrolTask
 * @param id
 */
export const delPatrol_task = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yfxc/patrol_task/' + id,
    method: 'delete'
  });
};
