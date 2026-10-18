import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SafetyEducationMstForm, SafetyEducationMstQuery, SafetyEducationMstVO } from './types';

// 查询列表
export function listSafetyEducationMst(query?: SafetyEducationMstQuery): AxiosPromise<SafetyEducationMstVO[]> {
  return request({
    url: '/pxjl/education_training_mst/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSafetyEducationMst(id: string | number): AxiosPromise<SafetyEducationMstVO> {
  return request({
    url: '/pxjl/education_training_mst/' + id,
    method: 'get'
  })
}

// 新增
export function addSafetyEducationMst(data: SafetyEducationMstForm) {
  return request({
    url: '/pxjl/education_training_mst',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSafetyEducationMst(data: SafetyEducationMstForm) {
  return request({
    url: '/pxjl/education_training_mst',
    method: 'put',
    data: data
  })
}

// 删除
export function delSafetyEducationMst(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxjl/education_training_mst/' + ids,
    method: 'delete'
  })
}