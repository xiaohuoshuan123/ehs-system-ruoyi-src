import type { Brscore_standardForm, Brscore_standardQuery, Brscore_standardVO } from '@/api/wztk/brscore_standard/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询BrscoreStandard列表
 * @param query
 * @returns {*}
 */
export const listBrscore_standard = (query?: Brscore_standardQuery): AxiosPromise<PageResult<Brscore_standardVO>> => {
  return request({
    url: '/wztk/brscore_standard/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询BrscoreStandard详细
 * @param id
 */
export const getBrscore_standard = (id: string | number): AxiosPromise<Brscore_standardVO> => {
  return request({
    url: '/wztk/brscore_standard/' + id,
    method: 'get'
  });
};

/**
 * 新增BrscoreStandard
 * @param data
 */
export const addBrscore_standard = (data: Brscore_standardForm) => {
  return request({
    url: '/wztk/brscore_standard',
    method: 'post',
    data: data
  });
};

/**
 * 修改BrscoreStandard
 * @param data
 */
export const updateBrscore_standard = (data: Brscore_standardForm) => {
  return request({
    url: '/wztk/brscore_standard',
    method: 'put',
    data: data
  });
};



/**
 * 删除BrscoreStandard
 * @param id
 */
export const delBrscore_standard = (id: string | number | Array<string | number>) => {
  return request({
    url: '/wztk/brscore_standard/' + id,
    method: 'delete'
  });
};
