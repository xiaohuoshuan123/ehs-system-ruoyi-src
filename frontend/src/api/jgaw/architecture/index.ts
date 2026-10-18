import type { ArchitectureForm, ArchitectureQuery, ArchitectureVO } from '@/api/jgaw/architecture/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Architecture列表
 * @param query
 * @returns {*}
 */
export const listArchitecture = (query?: ArchitectureQuery): AxiosPromise<PageResult<ArchitectureVO>> => {
  return request({
    url: '/jgaw/architecture/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Architecture详细
 * @param id
 */
export const getArchitecture = (id: string | number): AxiosPromise<ArchitectureVO> => {
  return request({
    url: '/jgaw/architecture/' + id,
    method: 'get'
  });
};

/**
 * 新增Architecture
 * @param data
 */
export const addArchitecture = (data: ArchitectureForm) => {
  return request({
    url: '/jgaw/architecture',
    method: 'post',
    data: data
  });
};

/**
 * 修改Architecture
 * @param data
 */
export const updateArchitecture = (data: ArchitectureForm) => {
  return request({
    url: '/jgaw/architecture',
    method: 'put',
    data: data
  });
};



/**
 * 删除Architecture
 * @param id
 */
export const delArchitecture = (id: string | number | Array<string | number>) => {
  return request({
    url: '/jgaw/architecture/' + id,
    method: 'delete'
  });
};
