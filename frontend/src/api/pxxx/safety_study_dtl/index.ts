import type { Safety_study_dtlForm, Safety_study_dtlQuery, Safety_study_dtlVO } from '@/api/pxxx/safety_study_dtl/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SafetyStudyDtl列表
 * @param query
 * @returns {*}
 */
export const listSafety_study_dtl = (query?: Safety_study_dtlQuery): AxiosPromise<PageResult<Safety_study_dtlVO>> => {
  return request({
    url: '/pxxx/safety_study_dtl/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SafetyStudyDtl详细
 * @param id
 */
export const getSafety_study_dtl = (id: string | number): AxiosPromise<Safety_study_dtlVO> => {
  return request({
    url: '/pxxx/safety_study_dtl/' + id,
    method: 'get'
  });
};

/**
 * 新增SafetyStudyDtl
 * @param data
 */
export const addSafety_study_dtl = (data: Safety_study_dtlForm) => {
  return request({
    url: '/pxxx/safety_study_dtl',
    method: 'post',
    data: data
  });
};

/**
 * 修改SafetyStudyDtl
 * @param data
 */
export const updateSafety_study_dtl = (data: Safety_study_dtlForm) => {
  return request({
    url: '/pxxx/safety_study_dtl',
    method: 'put',
    data: data
  });
};



/**
 * 删除SafetyStudyDtl
 * @param id
 */
export const delSafety_study_dtl = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxxx/safety_study_dtl/' + id,
    method: 'delete'
  });
};
