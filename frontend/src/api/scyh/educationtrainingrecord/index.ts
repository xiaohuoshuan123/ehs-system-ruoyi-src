import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { EducationTrainingRecordForm, EducationTrainingRecordQuery, EducationTrainingRecordVO } from './types';

// 查询列表
export function listEducationTrainingRecord(query?: EducationTrainingRecordQuery): AxiosPromise<EducationTrainingRecordVO[]> {
  return request({
    url: '/pxjl/education_training_record/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getEducationTrainingRecord(id: string | number): AxiosPromise<EducationTrainingRecordVO> {
  return request({
    url: '/pxjl/education_training_record/' + id,
    method: 'get'
  })
}

// 新增
export function addEducationTrainingRecord(data: EducationTrainingRecordForm) {
  return request({
    url: '/pxjl/education_training_record',
    method: 'post',
    data: data
  })
}

// 修改
export function updateEducationTrainingRecord(data: EducationTrainingRecordForm) {
  return request({
    url: '/pxjl/education_training_record',
    method: 'put',
    data: data
  })
}

// 删除
export function delEducationTrainingRecord(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxjl/education_training_record/' + ids,
    method: 'delete'
  })
}