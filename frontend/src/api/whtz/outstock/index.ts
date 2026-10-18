import type { OutstockForm, OutstockQuery, OutstockVO } from '@/api/whtz/outstock/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Outstock列表
 * @param query
 * @returns {*}
 */
export const listOutstock = (query?: OutstockQuery): AxiosPromise<PageResult<OutstockVO>> => {
  return request({
    url: '/whtz/outstock/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Outstock详细
 * @param id
 */
export const getOutstock = (id: string | number): AxiosPromise<OutstockVO> => {
  return request({
    url: '/whtz/outstock/' + id,
    method: 'get'
  });
};

/**
 * 新增Outstock
 * @param data
 */
export const addOutstock = (data: OutstockForm) => {
  return request({
    url: '/whtz/outstock',
    method: 'post',
    data: data
  });
};

/**
 * 修改Outstock
 * @param data
 */
export const updateOutstock = (data: OutstockForm) => {
  return request({
    url: '/whtz/outstock',
    method: 'put',
    data: data
  });
};



/**
 * 删除Outstock
 * @param id
 */
export const delOutstock = (id: string | number | Array<string | number>) => {
  return request({
    url: '/whtz/outstock/' + id,
    method: 'delete'
  });
};
