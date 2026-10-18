import type { HiddentroubleForm, HiddentroubleQuery, HiddentroubleVO } from '@/api/scyh/hiddentrouble/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Hiddentrouble列表
 * @param query
 * @returns {*}
 */
export const listHiddentrouble = (query?: HiddentroubleQuery): AxiosPromise<PageResult<HiddentroubleVO>> => {
  return request({
    url: '/scyh/hiddentrouble/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Hiddentrouble详细
 * @param id
 */
export const getHiddentrouble = (id: string | number): AxiosPromise<HiddentroubleVO> => {
  return request({
    url: '/scyh/hiddentrouble/' + id,
    method: 'get'
  });
};

/**
 * 新增Hiddentrouble
 * @param data
 */
export const addHiddentrouble = (data: HiddentroubleForm) => {
  return request({
    url: '/scyh/hiddentrouble',
    method: 'post',
    data: data
  });
};

/**
 * 修改Hiddentrouble
 * @param data
 */
export const updateHiddentrouble = (data: HiddentroubleForm) => {
  return request({
    url: '/scyh/hiddentrouble',
    method: 'put',
    data: data
  });
};



/**
 * 删除Hiddentrouble
 * @param id
 */
export const delHiddentrouble = (id: string | number | Array<string | number>) => {
  return request({
    url: '/scyh/hiddentrouble/' + id,
    method: 'delete'
  });
};
