import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { EducationTrainingMstForm, EducationTrainingMstQuery, EducationTrainingMstVO } from './types';

// 查询列表
export function listEducationTrainingMst(query?: EducationTrainingMstQuery): AxiosPromise<EducationTrainingMstVO[]> {
  return request({
    url: '/pxjl/education_training_mst/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getEducationTrainingMst(id: string | number): AxiosPromise<EducationTrainingMstVO> {
  return request({
    url: '/pxjl/education_training_mst/' + id,
    method: 'get'
  })
}

// 新增
export function addEducationTrainingMst(data: EducationTrainingMstForm) {
  return request({
    url: '/pxjl/education_training_mst',
    method: 'post',
    data: data
  })
}

// 修改
export function updateEducationTrainingMst(data: EducationTrainingMstForm) {
  return request({
    url: '/pxjl/education_training_mst',
    method: 'put',
    data: data
  })
}

// 删除
export function delEducationTrainingMst(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxjl/education_training_mst/' + ids,
    method: 'delete'
  })
}