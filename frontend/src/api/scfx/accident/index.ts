import type { AccidentForm, AccidentQuery, AccidentVO } from '@/api/scfx/accident/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Accident列表
 * @param query
 * @returns {*}
 */
export const listAccident = (query?: AccidentQuery): AxiosPromise<PageResult<AccidentVO>> => {
  return request({
    url: '/scfx/accident/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Accident详细
 * @param id
 */
export const getAccident = (id: string | number): AxiosPromise<AccidentVO> => {
  return request({
    url: '/scfx/accident/' + id,
    method: 'get'
  });
};

/**
 * 新增Accident
 * @param data
 */
export const addAccident = (data: AccidentForm) => {
  return request({
    url: '/scfx/accident',
    method: 'post',
    data: data
  });
};

/**
 * 修改Accident
 * @param data
 */
export const updateAccident = (data: AccidentForm) => {
  return request({
    url: '/scfx/accident',
    method: 'put',
    data: data
  });
};



/**
 * 删除Accident
 * @param id
 */
export const delAccident = (id: string | number | Array<string | number>) => {
  return request({
    url: '/scfx/accident/' + id,
    method: 'delete'
  });
};
