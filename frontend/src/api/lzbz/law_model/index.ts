import type { Law_modelForm, Law_modelQuery, Law_modelVO } from '@/api/lzbz/law_model/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询LawModel列表
 * @param query
 * @returns {*}
 */
export const listLaw_model = (query?: Law_modelQuery): AxiosPromise<PageResult<Law_modelVO>> => {
  return request({
    url: '/lzbz/law_model/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询LawModel详细
 * @param id
 */
export const getLaw_model = (id: string | number): AxiosPromise<Law_modelVO> => {
  return request({
    url: '/lzbz/law_model/' + id,
    method: 'get'
  });
};

/**
 * 新增LawModel
 * @param data
 */
export const addLaw_model = (data: Law_modelForm) => {
  return request({
    url: '/lzbz/law_model',
    method: 'post',
    data: data
  });
};

/**
 * 修改LawModel
 * @param data
 */
export const updateLaw_model = (data: Law_modelForm) => {
  return request({
    url: '/lzbz/law_model',
    method: 'put',
    data: data
  });
};



/**
 * 删除LawModel
 * @param id
 */
export const delLaw_model = (id: string | number | Array<string | number>) => {
  return request({
    url: '/lzbz/law_model/' + id,
    method: 'delete'
  });
};
