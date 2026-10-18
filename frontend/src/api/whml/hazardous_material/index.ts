import type { Hazardous_materialForm, Hazardous_materialQuery, Hazardous_materialVO } from '@/api/whml/hazardous_material/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询HazardousMaterial列表
 * @param query
 * @returns {*}
 */
export const listHazardous_material = (query?: Hazardous_materialQuery): AxiosPromise<PageResult<Hazardous_materialVO>> => {
  return request({
    url: '/whml/hazardous_material/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询HazardousMaterial详细
 * @param id
 */
export const getHazardous_material = (id: string | number): AxiosPromise<Hazardous_materialVO> => {
  return request({
    url: '/whml/hazardous_material/' + id,
    method: 'get'
  });
};

/**
 * 新增HazardousMaterial
 * @param data
 */
export const addHazardous_material = (data: Hazardous_materialForm) => {
  return request({
    url: '/whml/hazardous_material',
    method: 'post',
    data: data
  });
};

/**
 * 修改HazardousMaterial
 * @param data
 */
export const updateHazardous_material = (data: Hazardous_materialForm) => {
  return request({
    url: '/whml/hazardous_material',
    method: 'put',
    data: data
  });
};



/**
 * 删除HazardousMaterial
 * @param id
 */
export const delHazardous_material = (id: string | number | Array<string | number>) => {
  return request({
    url: '/whml/hazardous_material/' + id,
    method: 'delete'
  });
};
