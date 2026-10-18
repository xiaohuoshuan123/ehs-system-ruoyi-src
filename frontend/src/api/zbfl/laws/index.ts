import type { LawsForm, LawsQuery, LawsVO } from '@/api/zbfl/laws/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询Laws列表
 * @param query
 * @returns {*}
 */
export const listLaws = (query?: LawsQuery): AxiosPromise<PageResult<LawsVO>> => {
  return request({
    url: '/zbfl/laws/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询Laws详细
 * @param id
 */
export const getLaws = (id: string | number): AxiosPromise<LawsVO> => {
  return request({
    url: '/zbfl/laws/' + id,
    method: 'get'
  });
};

/**
 * 新增Laws
 * @param data
 */
export const addLaws = (data: LawsForm) => {
  return request({
    url: '/zbfl/laws',
    method: 'post',
    data: data
  });
};

/**
 * 修改Laws
 * @param data
 */
export const updateLaws = (data: LawsForm) => {
  return request({
    url: '/zbfl/laws',
    method: 'put',
    data: data
  });
};



/**
 * 删除Laws
 * @param id
 */
export const delLaws = (id: string | number | Array<string | number>) => {
  return request({
    url: '/zbfl/laws/' + id,
    method: 'delete'
  });
};
