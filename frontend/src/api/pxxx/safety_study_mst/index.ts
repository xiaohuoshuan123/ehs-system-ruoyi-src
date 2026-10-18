import type { Safety_study_mstForm, Safety_study_mstQuery, Safety_study_mstVO } from '@/api/pxxx/safety_study_mst/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SafetyStudyMst列表
 * @param query
 * @returns {*}
 */
export const listSafety_study_mst = (query?: Safety_study_mstQuery): AxiosPromise<PageResult<Safety_study_mstVO>> => {
  return request({
    url: '/pxxx/safety_study_mst/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SafetyStudyMst详细
 * @param id
 */
export const getSafety_study_mst = (id: string | number): AxiosPromise<Safety_study_mstVO> => {
  return request({
    url: '/pxxx/safety_study_mst/' + id,
    method: 'get'
  });
};

/**
 * 新增SafetyStudyMst
 * @param data
 */
export const addSafety_study_mst = (data: Safety_study_mstForm) => {
  return request({
    url: '/pxxx/safety_study_mst',
    method: 'post',
    data: data
  });
};

/**
 * 修改SafetyStudyMst
 * @param data
 */
export const updateSafety_study_mst = (data: Safety_study_mstForm) => {
  return request({
    url: '/pxxx/safety_study_mst',
    method: 'put',
    data: data
  });
};



/**
 * 删除SafetyStudyMst
 * @param id
 */
export const delSafety_study_mst = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxxx/safety_study_mst/' + id,
    method: 'delete'
  });
};
