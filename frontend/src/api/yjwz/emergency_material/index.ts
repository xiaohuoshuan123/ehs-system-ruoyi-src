import type { Emergency_materialForm, Emergency_materialQuery, Emergency_materialVO } from '@/api/yjwz/emergency_material/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EmergencyMaterial列表
 * @param query
 * @returns {*}
 */
export const listEmergency_material = (query?: Emergency_materialQuery): AxiosPromise<PageResult<Emergency_materialVO>> => {
  return request({
    url: '/yjwz/emergency_material/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EmergencyMaterial详细
 * @param id
 */
export const getEmergency_material = (id: string | number): AxiosPromise<Emergency_materialVO> => {
  return request({
    url: '/yjwz/emergency_material/' + id,
    method: 'get'
  });
};

/**
 * 新增EmergencyMaterial
 * @param data
 */
export const addEmergency_material = (data: Emergency_materialForm) => {
  return request({
    url: '/yjwz/emergency_material',
    method: 'post',
    data: data
  });
};

/**
 * 修改EmergencyMaterial
 * @param data
 */
export const updateEmergency_material = (data: Emergency_materialForm) => {
  return request({
    url: '/yjwz/emergency_material',
    method: 'put',
    data: data
  });
};



/**
 * 删除EmergencyMaterial
 * @param id
 */
export const delEmergency_material = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yjwz/emergency_material/' + id,
    method: 'delete'
  });
};
