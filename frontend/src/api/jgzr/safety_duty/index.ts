import type { Safety_dutyForm, Safety_dutyQuery, Safety_dutyVO } from '@/api/jgzr/safety_duty/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SafetyDuty列表
 * @param query
 * @returns {*}
 */
export const listSafety_duty = (query?: Safety_dutyQuery): AxiosPromise<PageResult<Safety_dutyVO>> => {
  return request({
    url: '/jgzr/safety_duty/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SafetyDuty详细
 * @param id
 */
export const getSafety_duty = (id: string | number): AxiosPromise<Safety_dutyVO> => {
  return request({
    url: '/jgzr/safety_duty/' + id,
    method: 'get'
  });
};

/**
 * 新增SafetyDuty
 * @param data
 */
export const addSafety_duty = (data: Safety_dutyForm) => {
  return request({
    url: '/jgzr/safety_duty',
    method: 'post',
    data: data
  });
};

/**
 * 修改SafetyDuty
 * @param data
 */
export const updateSafety_duty = (data: Safety_dutyForm) => {
  return request({
    url: '/jgzr/safety_duty',
    method: 'put',
    data: data
  });
};



/**
 * 删除SafetyDuty
 * @param id
 */
export const delSafety_duty = (id: string | number | Array<string | number>) => {
  return request({
    url: '/jgzr/safety_duty/' + id,
    method: 'delete'
  });
};
