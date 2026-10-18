import type { Emergency_equipmentForm, Emergency_equipmentQuery, Emergency_equipmentVO } from '@/api/yfqc/emergency_equipment/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EmergencyEquipment列表
 * @param query
 * @returns {*}
 */
export const listEmergency_equipment = (query?: Emergency_equipmentQuery): AxiosPromise<PageResult<Emergency_equipmentVO>> => {
  return request({
    url: '/yfqc/emergency_equipment/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EmergencyEquipment详细
 * @param id
 */
export const getEmergency_equipment = (id: string | number): AxiosPromise<Emergency_equipmentVO> => {
  return request({
    url: '/yfqc/emergency_equipment/' + id,
    method: 'get'
  });
};

/**
 * 新增EmergencyEquipment
 * @param data
 */
export const addEmergency_equipment = (data: Emergency_equipmentForm) => {
  return request({
    url: '/yfqc/emergency_equipment',
    method: 'post',
    data: data
  });
};

/**
 * 修改EmergencyEquipment
 * @param data
 */
export const updateEmergency_equipment = (data: Emergency_equipmentForm) => {
  return request({
    url: '/yfqc/emergency_equipment',
    method: 'put',
    data: data
  });
};



/**
 * 删除EmergencyEquipment
 * @param id
 */
export const delEmergency_equipment = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yfqc/emergency_equipment/' + id,
    method: 'delete'
  });
};
