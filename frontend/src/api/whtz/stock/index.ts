import type { StockForm, StockQuery, StockVO } from '@/api/whtz/stock/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Stock列表
 * @param query
 * @returns {*}
 */
export const listStock = (query?: StockQuery): AxiosPromise<PageResult<StockVO>> => {
  return request({
    url: '/whtz/stock/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Stock详细
 * @param id
 */
export const getStock = (id: string | number): AxiosPromise<StockVO> => {
  return request({
    url: '/whtz/stock/' + id,
    method: 'get'
  });
};

/**
 * 新增Stock
 * @param data
 */
export const addStock = (data: StockForm) => {
  return request({
    url: '/whtz/stock',
    method: 'post',
    data: data
  });
};

/**
 * 修改Stock
 * @param data
 */
export const updateStock = (data: StockForm) => {
  return request({
    url: '/whtz/stock',
    method: 'put',
    data: data
  });
};



/**
 * 删除Stock
 * @param id
 */
export const delStock = (id: string | number | Array<string | number>) => {
  return request({
    url: '/whtz/stock/' + id,
    method: 'delete'
  });
};
