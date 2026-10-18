import type { InstockForm, InstockQuery, InstockVO } from '@/api/whtz/instock/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Instock列表
 * @param query
 * @returns {*}
 */
export const listInstock = (query?: InstockQuery): AxiosPromise<PageResult<InstockVO>> => {
  return request({
    url: '/whtz/instock/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Instock详细
 * @param id
 */
export const getInstock = (id: string | number): AxiosPromise<InstockVO> => {
  return request({
    url: '/whtz/instock/' + id,
    method: 'get'
  });
};

/**
 * 新增Instock
 * @param data
 */
export const addInstock = (data: InstockForm) => {
  return request({
    url: '/whtz/instock',
    method: 'post',
    data: data
  });
};

/**
 * 修改Instock
 * @param data
 */
export const updateInstock = (data: InstockForm) => {
  return request({
    url: '/whtz/instock',
    method: 'put',
    data: data
  });
};



/**
 * 删除Instock
 * @param id
 */
export const delInstock = (id: string | number | Array<string | number>) => {
  return request({
    url: '/whtz/instock/' + id,
    method: 'delete'
  });
};
