import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SafetyManagementForm, SafetyManagementQuery, SafetyManagementVO } from './types';

// 查询列表
export function listSafetyManagement(query?: SafetyManagementQuery): AxiosPromise<SafetyManagementVO[]> {
  return request({
    url: '/pxzs/enterprise_person/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSafetyManagement(id: string | number): AxiosPromise<SafetyManagementVO> {
  return request({
    url: '/pxzs/enterprise_person/' + id,
    method: 'get'
  })
}

// 新增
export function addSafetyManagement(data: SafetyManagementForm) {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSafetyManagement(data: SafetyManagementForm) {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'put',
    data: data
  })
}

// 删除
export function delSafetyManagement(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxzs/enterprise_person/' + ids,
    method: 'delete'
  })
}