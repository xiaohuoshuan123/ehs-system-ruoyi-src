import type { Education_training_mstForm, Education_training_mstQuery, Education_training_mstVO } from '@/api/pxjl/education_training_mst/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EducationTrainingMst列表
 * @param query
 * @returns {*}
 */
export const listEducation_training_mst = (query?: Education_training_mstQuery): AxiosPromise<PageResult<Education_training_mstVO>> => {
  return request({
    url: '/pxjl/education_training_mst/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EducationTrainingMst详细
 * @param id
 */
export const getEducation_training_mst = (id: string | number): AxiosPromise<Education_training_mstVO> => {
  return request({
    url: '/pxjl/education_training_mst/' + id,
    method: 'get'
  });
};

/**
 * 新增EducationTrainingMst
 * @param data
 */
export const addEducation_training_mst = (data: Education_training_mstForm) => {
  return request({
    url: '/pxjl/education_training_mst',
    method: 'post',
    data: data
  });
};

/**
 * 修改EducationTrainingMst
 * @param data
 */
export const updateEducation_training_mst = (data: Education_training_mstForm) => {
  return request({
    url: '/pxjl/education_training_mst',
    method: 'put',
    data: data
  });
};



/**
 * 删除EducationTrainingMst
 * @param id
 */
export const delEducation_training_mst = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxjl/education_training_mst/' + id,
    method: 'delete'
  });
};
