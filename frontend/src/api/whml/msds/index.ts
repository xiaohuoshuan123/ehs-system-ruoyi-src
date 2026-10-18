import type { MsdsForm, MsdsQuery, MsdsVO } from '@/api/whml/msds/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Msds列表
 * @param query
 * @returns {*}
 */
export const listMsds = (query?: MsdsQuery): AxiosPromise<PageResult<MsdsVO>> => {
  return request({
    url: '/whml/msds/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Msds详细
 * @param id
 */
export const getMsds = (id: string | number): AxiosPromise<MsdsVO> => {
  return request({
    url: '/whml/msds/' + id,
    method: 'get'
  });
};

/**
 * 新增Msds
 * @param data
 */
export const addMsds = (data: MsdsForm) => {
  return request({
    url: '/whml/msds',
    method: 'post',
    data: data
  });
};

/**
 * 修改Msds
 * @param data
 */
export const updateMsds = (data: MsdsForm) => {
  return request({
    url: '/whml/msds',
    method: 'put',
    data: data
  });
};



/**
 * 删除Msds
 * @param id
 */
export const delMsds = (id: string | number | Array<string | number>) => {
  return request({
    url: '/whml/msds/' + id,
    method: 'delete'
  });
};
