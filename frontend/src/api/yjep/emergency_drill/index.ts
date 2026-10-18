import type { Emergency_drillForm, Emergency_drillQuery, Emergency_drillVO } from '@/api/yjep/emergency_drill/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EmergencyDrill列表
 * @param query
 * @returns {*}
 */
export const listEmergency_drill = (query?: Emergency_drillQuery): AxiosPromise<PageResult<Emergency_drillVO>> => {
  return request({
    url: '/yjep/emergency_drill/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EmergencyDrill详细
 * @param id
 */
export const getEmergency_drill = (id: string | number): AxiosPromise<Emergency_drillVO> => {
  return request({
    url: '/yjep/emergency_drill/' + id,
    method: 'get'
  });
};

/**
 * 新增EmergencyDrill
 * @param data
 */
export const addEmergency_drill = (data: Emergency_drillForm) => {
  return request({
    url: '/yjep/emergency_drill',
    method: 'post',
    data: data
  });
};

/**
 * 修改EmergencyDrill
 * @param data
 */
export const updateEmergency_drill = (data: Emergency_drillForm) => {
  return request({
    url: '/yjep/emergency_drill',
    method: 'put',
    data: data
  });
};



/**
 * 删除EmergencyDrill
 * @param id
 */
export const delEmergency_drill = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yjep/emergency_drill/' + id,
    method: 'delete'
  });
};
