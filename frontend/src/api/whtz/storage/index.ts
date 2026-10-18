import type { StorageForm, StorageQuery, StorageVO } from '@/api/whtz/storage/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Storage列表
 * @param query
 * @returns {*}
 */
export const listStorage = (query?: StorageQuery): AxiosPromise<PageResult<StorageVO>> => {
  return request({
    url: '/whtz/storage/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Storage详细
 * @param id
 */
export const getStorage = (id: string | number): AxiosPromise<StorageVO> => {
  return request({
    url: '/whtz/storage/' + id,
    method: 'get'
  });
};

/**
 * 新增Storage
 * @param data
 */
export const addStorage = (data: StorageForm) => {
  return request({
    url: '/whtz/storage',
    method: 'post',
    data: data
  });
};

/**
 * 修改Storage
 * @param data
 */
export const updateStorage = (data: StorageForm) => {
  return request({
    url: '/whtz/storage',
    method: 'put',
    data: data
  });
};



/**
 * 删除Storage
 * @param id
 */
export const delStorage = (id: string | number | Array<string | number>) => {
  return request({
    url: '/whtz/storage/' + id,
    method: 'delete'
  });
};
