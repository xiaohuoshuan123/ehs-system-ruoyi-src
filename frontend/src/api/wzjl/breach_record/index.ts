import type { Breach_recordForm, Breach_recordQuery, Breach_recordVO } from '@/api/wzjl/breach_record/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询BreachRecord列表
 * @param query
 * @returns {*}
 */
export const listBreach_record = (query?: Breach_recordQuery): AxiosPromise<PageResult<Breach_recordVO>> => {
  return request({
    url: '/wzjl/breach_record/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询BreachRecord详细
 * @param id
 */
export const getBreach_record = (id: string | number): AxiosPromise<Breach_recordVO> => {
  return request({
    url: '/wzjl/breach_record/' + id,
    method: 'get'
  });
};

/**
 * 新增BreachRecord
 * @param data
 */
export const addBreach_record = (data: Breach_recordForm) => {
  return request({
    url: '/wzjl/breach_record',
    method: 'post',
    data: data
  });
};

/**
 * 修改BreachRecord
 * @param data
 */
export const updateBreach_record = (data: Breach_recordForm) => {
  return request({
    url: '/wzjl/breach_record',
    method: 'put',
    data: data
  });
};



/**
 * 删除BreachRecord
 * @param id
 */
export const delBreach_record = (id: string | number | Array<string | number>) => {
  return request({
    url: '/wzjl/breach_record/' + id,
    method: 'delete'
  });
};
