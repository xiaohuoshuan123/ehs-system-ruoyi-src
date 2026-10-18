import type { Special_workForm, Special_workQuery, Special_workVO } from '@/api/pxzs/special_work/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SpecialWork列表
 * @param query
 * @returns {*}
 */
export const listSpecial_work = (query?: Special_workQuery): AxiosPromise<PageResult<Special_workVO>> => {
  return request({
    url: '/pxzs/special_work/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SpecialWork详细
 * @param id
 */
export const getSpecial_work = (id: string | number): AxiosPromise<Special_workVO> => {
  return request({
    url: '/pxzs/special_work/' + id,
    method: 'get'
  });
};

/**
 * 新增SpecialWork
 * @param data
 */
export const addSpecial_work = (data: Special_workForm) => {
  return request({
    url: '/pxzs/special_work',
    method: 'post',
    data: data
  });
};

/**
 * 修改SpecialWork
 * @param data
 */
export const updateSpecial_work = (data: Special_workForm) => {
  return request({
    url: '/pxzs/special_work',
    method: 'put',
    data: data
  });
};



/**
 * 删除SpecialWork
 * @param id
 */
export const delSpecial_work = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxzs/special_work/' + id,
    method: 'delete'
  });
};
