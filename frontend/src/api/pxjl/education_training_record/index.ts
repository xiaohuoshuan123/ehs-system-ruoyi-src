import type { Education_training_recordForm, Education_training_recordQuery, Education_training_recordVO } from '@/api/pxjl/education_training_record/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EducationTrainingRecord列表
 * @param query
 * @returns {*}
 */
export const listEducation_training_record = (query?: Education_training_recordQuery): AxiosPromise<PageResult<Education_training_recordVO>> => {
  return request({
    url: '/pxjl/education_training_record/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EducationTrainingRecord详细
 * @param id
 */
export const getEducation_training_record = (id: string | number): AxiosPromise<Education_training_recordVO> => {
  return request({
    url: '/pxjl/education_training_record/' + id,
    method: 'get'
  });
};

/**
 * 新增EducationTrainingRecord
 * @param data
 */
export const addEducation_training_record = (data: Education_training_recordForm) => {
  return request({
    url: '/pxjl/education_training_record',
    method: 'post',
    data: data
  });
};

/**
 * 修改EducationTrainingRecord
 * @param data
 */
export const updateEducation_training_record = (data: Education_training_recordForm) => {
  return request({
    url: '/pxjl/education_training_record',
    method: 'put',
    data: data
  });
};



/**
 * 删除EducationTrainingRecord
 * @param id
 */
export const delEducation_training_record = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxjl/education_training_record/' + id,
    method: 'delete'
  });
};
