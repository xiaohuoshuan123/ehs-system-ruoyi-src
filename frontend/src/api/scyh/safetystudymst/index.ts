import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SafetyStudyMstForm, SafetyStudyMstQuery, SafetyStudyMstVO } from './types';

// 查询列表
export function listSafetyStudyMst(query?: SafetyStudyMstQuery): AxiosPromise<SafetyStudyMstVO[]> {
  return request({
    url: '/pxxx/safety_study_mst/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSafetyStudyMst(id: string | number): AxiosPromise<SafetyStudyMstVO> {
  return request({
    url: '/pxxx/safety_study_mst/' + id,
    method: 'get'
  })
}

// 新增
export function addSafetyStudyMst(data: SafetyStudyMstForm) {
  return request({
    url: '/pxxx/safety_study_mst',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSafetyStudyMst(data: SafetyStudyMstForm) {
  return request({
    url: '/pxxx/safety_study_mst',
    method: 'put',
    data: data
  })
}

// 删除
export function delSafetyStudyMst(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxxx/safety_study_mst/' + ids,
    method: 'delete'
  })
}