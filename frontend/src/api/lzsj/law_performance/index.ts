import type { Law_performanceForm, Law_performanceQuery, Law_performanceVO } from '@/api/lzsj/law_performance/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询LawPerformance列表
 * @param query
 * @returns {*}
 */
export const listLaw_performance = (query?: Law_performanceQuery): AxiosPromise<PageResult<Law_performanceVO>> => {
  return request({
    url: '/lzsj/law_performance/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询LawPerformance详细
 * @param id
 */
export const getLaw_performance = (id: string | number): AxiosPromise<Law_performanceVO> => {
  return request({
    url: '/lzsj/law_performance/' + id,
    method: 'get'
  });
};

/**
 * 新增LawPerformance
 * @param data
 */
export const addLaw_performance = (data: Law_performanceForm) => {
  return request({
    url: '/lzsj/law_performance',
    method: 'post',
    data: data
  });
};

/**
 * 修改LawPerformance
 * @param data
 */
export const updateLaw_performance = (data: Law_performanceForm) => {
  return request({
    url: '/lzsj/law_performance',
    method: 'put',
    data: data
  });
};



/**
 * 删除LawPerformance
 * @param id
 */
export const delLaw_performance = (id: string | number | Array<string | number>) => {
  return request({
    url: '/lzsj/law_performance/' + id,
    method: 'delete'
  });
};
